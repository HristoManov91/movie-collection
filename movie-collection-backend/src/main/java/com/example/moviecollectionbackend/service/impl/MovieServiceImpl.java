package com.example.moviecollectionbackend.service.impl;

import com.example.moviecollectionbackend.exception.UserNotFoundException;
import com.example.moviecollectionbackend.model.binding.AddMovieBindingModel;
import com.example.moviecollectionbackend.model.binding.EditMovieBindingModel;
import com.example.moviecollectionbackend.model.dto.MovieCardDto;
import com.example.moviecollectionbackend.model.dto.MovieDetailsDto;
import com.example.moviecollectionbackend.model.dto.StatisticsDto;
import com.example.moviecollectionbackend.model.entity.GenreEntity;
import com.example.moviecollectionbackend.model.entity.MovieEntity;
import com.example.moviecollectionbackend.model.entity.PlatformEntity;
import com.example.moviecollectionbackend.repository.MovieRepository;
import com.example.moviecollectionbackend.service.GenreService;
import com.example.moviecollectionbackend.service.MovieService;
import com.example.moviecollectionbackend.service.PlatformService;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper;
    private final GenreService genreService;
    private final PlatformService platformService;

    public MovieServiceImpl(MovieRepository movieRepository, ModelMapper modelMapper, GenreService genreService, PlatformService platformService) {
        this.movieRepository = movieRepository;
        this.modelMapper = modelMapper;
        this.genreService = genreService;
        this.platformService = platformService;
    }

    @Override
    public MovieDetailsDto addMovie(AddMovieBindingModel addMovieBindingModel) throws URISyntaxException {

        MovieEntity movieEntity = mapDtoToEntity(addMovieBindingModel);

        List<GenreEntity> genres = genreService.findAllByNames(addMovieBindingModel.getGenres());
        movieEntity.setGenres(genres);

        List<PlatformEntity> platforms = platformService.findAllByNames(addMovieBindingModel.getPlatforms());
        movieEntity.setPlatforms(platforms);

        if (addMovieBindingModel.getImdbUrl() != null) {
            movieEntity.setRating(getIMDbRating(addMovieBindingModel.getImdbUrl()));
        }

        MovieEntity save = movieRepository.save(movieEntity);

        return mapEntityToDto(save);

    }

    @Override
    public MovieDetailsDto editMovie(EditMovieBindingModel editMovieBindingModel) {
        MovieEntity movieEntity = movieRepository.findById(editMovieBindingModel.getMovieId()).orElseThrow();

        if (!editMovieBindingModel.getTitle1().equals(movieEntity.getTitle1())) {
            movieEntity.setTitle1(editMovieBindingModel.getTitle1());
        }

        if (!Objects.equals(editMovieBindingModel.getDuration(), movieEntity.getDuration())) {
            movieEntity.setDuration(editMovieBindingModel.getDuration());
        }

        if (!Objects.equals(editMovieBindingModel.getYear(), movieEntity.getYear())) {
            movieEntity.setYear(editMovieBindingModel.getYear());
        }

        if (!editMovieBindingModel.getTrailerUrl().equals(movieEntity.getTrailerUrl())) {
            movieEntity.setTrailerUrl(editMovieBindingModel.getTrailerUrl());
        }

        if (!editMovieBindingModel.getPosterUrl().equals(movieEntity.getPosterUrl())) {
            movieEntity.setPosterUrl(editMovieBindingModel.getPosterUrl());
        }

        movieEntity.setTitle2(editMovieBindingModel.getTitle2());
        movieEntity.setImdbUrl(editMovieBindingModel.getImdbUrl());
        movieEntity.setDescription(editMovieBindingModel.getDescription());
        movieEntity.setBulgarianLanguage(editMovieBindingModel.getBulgarianLanguage());

        List<GenreEntity> genres = genreService.findAllByNames(editMovieBindingModel.getGenres());
        movieEntity.setGenres(genres);

        List<PlatformEntity> platforms = platformService.findAllByNames(editMovieBindingModel.getPlatforms());
        movieEntity.setPlatforms(platforms);

        MovieEntity save = movieRepository.save(movieEntity);

        return mapEntityToDto(save);
    }

    @Override
    public Boolean deleteMovieById(Long movieId) {
        boolean equals = movieRepository.findById(movieId).equals(Optional.empty());
        if (!equals) {
            movieRepository.deleteById(movieId);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Page<MovieCardDto> findAllMoviesWithPagination(Pageable pageable, Map<String, Object> params) {
        System.out.println();

        Page<MovieEntity> allMoviesCard = movieRepository
            .findAllMoviesCard(pageable,
                params.get("minDuration") != null && !("").equals(params.get("minDuration")) ? Optional.of((Integer) params.get("minDuration"))
                    : Optional.empty(),
                params.get("maxDuration") != null && !("").equals(params.get("maxDuration")) ? Optional.of((Integer) params.get("maxDuration"))
                    : Optional.empty(),
                params.get("minRating") != null && !("").equals(params.get("minRating")) ? Optional.of((Number) params.get("minRating"))
                    : Optional.empty(),
                params.get("maxRating") != null && !("").equals(params.get("maxRating")) ? Optional.of((Number) params.get("maxRating"))
                    : Optional.empty(),
                params.get("searchText") != null ? Optional.of((String) params.get("searchText"))
                    : Optional.empty(),
                params.get("genres") != null ? Optional.of((List<String>) params.get("genres")) : Optional.empty());

        Page<MovieCardDto> map = allMoviesCard.map(m -> modelMapper.map(m, MovieCardDto.class));

        return map;
    }

    @Override
    public List<MovieCardDto> findAllMovies() {
//        List<MovieEntity> allMovies = movieRepository.findAllMoviesCard();

//        return allMovies.stream().map(m -> {
//            MovieCardDto map = modelMapper.map(m, MovieCardDto.class);
//            return map;
//        }).toList();

        return null;
    }

    @Override
    public MovieDetailsDto getMovieDetailsDto(Long movieId) throws UserNotFoundException {
        MovieEntity movieEntity = movieRepository.findById(movieId)
            .orElseThrow(() -> new UserNotFoundException("User with this id " + movieId + " not found!"));

        return mapEntityToDto(movieEntity);
    }

    @Override
    public void updateRatings() throws URISyntaxException {
        List<MovieEntity> all = movieRepository.findAll();
        for (MovieEntity movie : all) {
            BigDecimal rating = getIMDbRating(movie.getImdbUrl());
            if (rating != null) {
                movie.setRating(rating);
                movieRepository.save(movie);
            }
        }
    }

    @Override
    public StatisticsDto getStatistics() {
       StatisticsDto statisticsDto = new StatisticsDto();

        statisticsDto.setTotalMovies(movieRepository.findTotal());
        statisticsDto.setTotalDurations(movieRepository.findTotalDurations());
        statisticsDto.setActionMovies(movieRepository.countMoviesByGenre("ACTION"));
        statisticsDto.setComedyMovies(movieRepository.countMoviesByGenre("COMEDY"));
        statisticsDto.setDramaMovies(movieRepository.countMoviesByGenre("DRAMA"));
        statisticsDto.setMysteryMovies(movieRepository.countMoviesByGenre("MYSTERY"));
        statisticsDto.setSciFiMovies(movieRepository.countMoviesByGenre("SCI-FI"));
        statisticsDto.setAnimationMovies(movieRepository.countMoviesByGenre("ANIMATION"));
        statisticsDto.setAdventureMovies(movieRepository.countMoviesByGenre("ADVENTURE"));
        statisticsDto.setFantasyMovies(movieRepository.countMoviesByGenre("FANTASY"));
        statisticsDto.setRomanceMovies(movieRepository.countMoviesByGenre("ROMANCE"));
        statisticsDto.setThrillerMovies(movieRepository.countMoviesByGenre("THRILLER"));
        statisticsDto.setCrimeMovies(movieRepository.countMoviesByGenre("CRIME"));
        statisticsDto.setDocumentaryMovies(movieRepository.countMoviesByGenre("DOCUMENTARY"));
        statisticsDto.setSerialMovies(movieRepository.countMoviesByGenre("SERIAL"));

        return statisticsDto;
    }

    private MovieDetailsDto mapEntityToDto(MovieEntity me) {
        MovieDetailsDto movieDetailsDto = new MovieDetailsDto()
            .setMovieId(me.getId())
            .setTitle1(me.getTitle1() != null ? me.getTitle1() : null)
            .setTitle2(me.getTitle2() != null ? me.getTitle2() : null)
            .setDuration(me.getDuration() != null ? me.getDuration() : null)
            .setDescription(me.getDescription() != null ? me.getDescription() : null)
            .setBulgarianLanguage(me.getBulgarianLanguage() != null ? me.getBulgarianLanguage() : null)
            .setRating(me.getRating() != null ? me.getRating() : null)
            .setTrailerUrl(me.getTrailerUrl() != null ? me.getTrailerUrl() : null)
            .setPosterUrl(me.getPosterUrl() != null ? me.getPosterUrl() : null)
            .setImdbUrl(me.getImdbUrl() != null ? me.getImdbUrl() : null)
            .setYear(me.getYear() != null ? me.getYear() : null);

        List<String> genres = genreService.findAllByMovieId(me.getId());
        movieDetailsDto.setGenres(genres);

        List<String> platforms = platformService.findAllByMovieId(me.getId());
        movieDetailsDto.setPlatforms(platforms);

        return movieDetailsDto;
    }

    private MovieEntity mapDtoToEntity(AddMovieBindingModel bindingModel) {
        return new MovieEntity()
            .setTitle1(bindingModel.getTitle1() != null ? bindingModel.getTitle1() : null)
            .setTitle2(bindingModel.getTitle2() != null ? bindingModel.getTitle2() : null)
            .setDuration(bindingModel.getDuration() != null ? bindingModel.getDuration() : null)
            .setDescription(bindingModel.getDescription() != null ? bindingModel.getDescription() : null)
            .setBulgarianLanguage(bindingModel.getBulgarianLanguage() != null ? bindingModel.getBulgarianLanguage() : null)
            .setTrailerUrl(bindingModel.getTrailerUrl() != null ? bindingModel.getTrailerUrl() : null)
            .setPosterUrl(bindingModel.getPosterUrl() != null ? bindingModel.getPosterUrl() : null)
            .setImdbUrl(bindingModel.getImdbUrl() != null ? bindingModel.getImdbUrl() : null)
            .setYear(bindingModel.getYear() != null ? bindingModel.getYear() : null);
    }

    private BigDecimal getIMDbRating(String IMDbURL) throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        URI uri = new URI(IMDbURL);

        ResponseEntity<String> forEntity = restTemplate.getForEntity(uri, String.class);
        String body = forEntity.getBody();

        String regex = "worstRating\\\":[0-9]+,\\\"ratingValue\\\":[0-9]\\.?[0-9]?";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(body);

        String result = "";
        while (matcher.find()) {
            result = matcher.group();
        }

        if (result.length() > 0) {
            int lastIndex = result.lastIndexOf(":");
            String substring = result.substring(lastIndex + 1);
            return new BigDecimal(substring);
        }

        return null;
    }
}
