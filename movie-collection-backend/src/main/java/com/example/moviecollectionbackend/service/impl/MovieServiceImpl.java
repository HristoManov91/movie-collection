package com.example.moviecollectionbackend.service.impl;

import com.example.moviecollectionbackend.exception.FullMovieCollectionException;
import com.example.moviecollectionbackend.exception.InvalidIMDbUrlException;
import com.example.moviecollectionbackend.exception.MovieNotFoundException;
import com.example.moviecollectionbackend.exception.UserNotFoundException;
import com.example.moviecollectionbackend.model.dto.AddMovieDTO;
import com.example.moviecollectionbackend.model.dto.EditMovieDTO;
import com.example.moviecollectionbackend.model.dto.MovieCardDto;
import com.example.moviecollectionbackend.model.dto.MovieDetailsDto;
import com.example.moviecollectionbackend.model.dto.StatisticsDto;
import com.example.moviecollectionbackend.model.entity.GenreEntity;
import com.example.moviecollectionbackend.model.entity.MovieEntity;
import com.example.moviecollectionbackend.model.entity.PlatformEntity;
import com.example.moviecollectionbackend.model.entity.UserEntity;
import com.example.moviecollectionbackend.repository.MovieRepository;
import com.example.moviecollectionbackend.service.GenreService;
import com.example.moviecollectionbackend.service.MovieService;
import com.example.moviecollectionbackend.service.PlatformService;
import com.example.moviecollectionbackend.service.UserService;
import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieServiceImpl implements MovieService {

    private final static Logger LOGGER = LoggerFactory.getLogger(MovieServiceImpl.class);

    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper;
    private final GenreService genreService;
    private final PlatformService platformService;
    private final UserService userService;

    public MovieServiceImpl(MovieRepository movieRepository, ModelMapper modelMapper, GenreService genreService, PlatformService platformService,
        UserService userService) {
        this.movieRepository = movieRepository;
        this.modelMapper = modelMapper;
        this.genreService = genreService;
        this.platformService = platformService;
        this.userService = userService;
    }

    @Override
    public MovieDetailsDto addMovie(Long userId, AddMovieDTO addMovieDTO) throws InvalidIMDbUrlException, UserNotFoundException, FullMovieCollectionException {

        if (movieRepository.findTotal(userId) == 2000){
            throw new FullMovieCollectionException("Your collection from movies is full,max movies is 2000!");
        }

        UserEntity userEntity = userService.findById(userId);

        MovieEntity movieEntity = mapDtoToEntity(addMovieDTO);
        movieEntity.setUser(userEntity);

        List<GenreEntity> genres = genreService.findAllByNames(addMovieDTO.getGenres());
        movieEntity.setGenres(genres);

        List<PlatformEntity> platforms = platformService.findAllByNames(addMovieDTO.getPlatforms());
        movieEntity.setPlatforms(platforms);

        if (addMovieDTO.getImdbUrl() != null) {
            movieEntity.setRating(getIMDbRating(addMovieDTO.getImdbUrl()));
        }

        MovieEntity save = movieRepository.save(movieEntity);

        return mapEntityToDto(save);

    }

    @Override
    public MovieDetailsDto editMovie(Long userId, EditMovieDTO editMovieDTO) throws MovieNotFoundException {
        MovieEntity movieEntity = movieRepository.findById(userId, editMovieDTO.getMovieId())
            .orElseThrow(() -> new MovieNotFoundException("You don't have movie with this id " + editMovieDTO.getMovieId() + " !"));

        if (!editMovieDTO.getTitle1().equals(movieEntity.getTitle1())) {
            movieEntity.setTitle1(editMovieDTO.getTitle1());
        }

        if (!Objects.equals(editMovieDTO.getDuration(), movieEntity.getDuration())) {
            movieEntity.setDuration(editMovieDTO.getDuration());
        }

        if (!Objects.equals(editMovieDTO.getYear(), movieEntity.getYear())) {
            movieEntity.setYear(editMovieDTO.getYear());
        }

        if (!editMovieDTO.getTrailerUrl().equals(movieEntity.getTrailerUrl())) {
            movieEntity.setTrailerUrl(editMovieDTO.getTrailerUrl());
        }

        if (!editMovieDTO.getPosterUrl().equals(movieEntity.getPosterUrl())) {
            movieEntity.setPosterUrl(editMovieDTO.getPosterUrl());
        }

        movieEntity.setTitle2(editMovieDTO.getTitle2());
        movieEntity.setImdbUrl(editMovieDTO.getImdbUrl());
        movieEntity.setDescription(editMovieDTO.getDescription());
        movieEntity.setBulgarianLanguage(editMovieDTO.getBulgarianLanguage());

        List<GenreEntity> genres = genreService.findAllByNames(editMovieDTO.getGenres());
        movieEntity.setGenres(genres);

        List<PlatformEntity> platforms = platformService.findAllByNames(editMovieDTO.getPlatforms());
        movieEntity.setPlatforms(platforms);

        MovieEntity save = movieRepository.save(movieEntity);

        return mapEntityToDto(save);
    }

    @Override
    public void deleteMovieById(Long userId, Long movieId) throws MovieNotFoundException {

        MovieEntity movieEntity = movieRepository.findById(movieId, userId)
            .orElseThrow(() -> new MovieNotFoundException("You don't have movie with this id " + movieId + " !"));

        movieRepository.delete(movieEntity);
    }

    @Override
    public Page<MovieCardDto> findAllMoviesWithPagination(Pageable pageable, Long userId, Map<String, Object> params) {

        if (movieRepository.findTotal(userId) == 0) {
            return null;
        }

        Page<MovieEntity> allMoviesCard = movieRepository
            .findAllMoviesCard(pageable, userId,
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
    public MovieDetailsDto getMovieDetailsDto(Long userId, Long movieId) throws MovieNotFoundException {

        MovieEntity movieEntity = movieRepository.findById(movieId, userId)
            .orElseThrow(() -> new MovieNotFoundException("You don't have movie with this id " + movieId + " !"));

        return mapEntityToDto(movieEntity);
    }

    @Override
    public void updateRatings() throws InvalidIMDbUrlException {
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
    public StatisticsDto getStatistics(Long userId) {
        StatisticsDto statisticsDto = new StatisticsDto();

        statisticsDto.setTotalMovies(movieRepository.findTotal(userId));
        statisticsDto.setTotalDurations(movieRepository.findTotalDurations(userId));
        statisticsDto.setActionMovies(movieRepository.countMoviesByGenre("ACTION", userId));
        statisticsDto.setComedyMovies(movieRepository.countMoviesByGenre("COMEDY", userId));
        statisticsDto.setDramaMovies(movieRepository.countMoviesByGenre("DRAMA", userId));
        statisticsDto.setMysteryMovies(movieRepository.countMoviesByGenre("MYSTERY", userId));
        statisticsDto.setSciFiMovies(movieRepository.countMoviesByGenre("SCI-FI", userId));
        statisticsDto.setAnimationMovies(movieRepository.countMoviesByGenre("ANIMATION", userId));
        statisticsDto.setAdventureMovies(movieRepository.countMoviesByGenre("ADVENTURE", userId));
        statisticsDto.setFantasyMovies(movieRepository.countMoviesByGenre("FANTASY", userId));
        statisticsDto.setRomanceMovies(movieRepository.countMoviesByGenre("ROMANCE", userId));
        statisticsDto.setThrillerMovies(movieRepository.countMoviesByGenre("THRILLER", userId));
        statisticsDto.setCrimeMovies(movieRepository.countMoviesByGenre("CRIME", userId));
        statisticsDto.setDocumentaryMovies(movieRepository.countMoviesByGenre("DOCUMENTARY", userId));
        statisticsDto.setSerialMovies(movieRepository.countMoviesByGenre("SERIAL", userId));

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

    private MovieEntity mapDtoToEntity(AddMovieDTO bindingModel) {
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

    private BigDecimal getIMDbRating(String IMDbURL) throws InvalidIMDbUrlException {

        try {
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
        } catch (Exception ex) {
            LOGGER.error("Invalid IMDb URL: " + IMDbURL);
            throw new InvalidIMDbUrlException("Invalid IMDb URL!");
        }

        return null;
    }

    private Long getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return 1L;
    }
}
