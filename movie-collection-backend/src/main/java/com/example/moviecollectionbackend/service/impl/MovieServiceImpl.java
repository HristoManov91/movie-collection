package com.example.moviecollectionbackend.service.impl;

import com.example.moviecollectionbackend.exception.FullMovieCollectionException;
import com.example.moviecollectionbackend.exception.InvalidIMDbUrlException;
import com.example.moviecollectionbackend.exception.MovieNotFoundException;
import com.example.moviecollectionbackend.model.dto.MovieCardDТО;
import com.example.moviecollectionbackend.model.dto.MovieDTO;
import com.example.moviecollectionbackend.model.dto.SearchParamsDTO;
import com.example.moviecollectionbackend.model.dto.StatisticsDТО;
import com.example.moviecollectionbackend.model.entity.MovieEntity;
import com.example.moviecollectionbackend.model.entity.UserEntity;
import com.example.moviecollectionbackend.model.mapper.MovieMapper;
import com.example.moviecollectionbackend.repository.MovieRepository;
import com.example.moviecollectionbackend.service.GenreService;
import com.example.moviecollectionbackend.service.MovieService;
import com.example.moviecollectionbackend.service.PlatformService;
import com.example.moviecollectionbackend.service.UserService;
import java.math.BigDecimal;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieServiceImpl implements MovieService {

    private final static Logger LOGGER = LoggerFactory.getLogger(MovieServiceImpl.class);

    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper;
    private final MovieMapper mapper;

    public MovieServiceImpl(MovieRepository movieRepository, ModelMapper modelMapper, MovieMapper mapper) {
        this.movieRepository = movieRepository;
        this.modelMapper = modelMapper;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public MovieDTO addMovie(Long userId, MovieDTO movieDTO) {

        if (movieRepository.findTotal(userId) == 2000) {
            throw new FullMovieCollectionException("Your collection from movies is full,max movies is 2000!");
        }

        movieDTO.setUserId(userId);

        MovieEntity movieEntity = mapper.mapToEntity(movieDTO);

        if (movieDTO.getImdbUrl() != null) {
            movieEntity.setRating(getIMDbRating(movieDTO.getImdbUrl()));
        }

        return mapper.mapToResource(movieRepository.save(movieEntity));

    }

    @Override
    @Transactional
    public MovieDTO editMovie(Long userId, MovieDTO movieDTO) {

        movieRepository.findById(movieDTO.getMovieId(), userId)
            .orElseThrow(() -> new MovieNotFoundException("You don't have movie with this id " + movieDTO.getMovieId() + " !"));

        return mapper.mapToResource(movieRepository.save(mapper.mapToEntity(movieDTO)));
    }

    @Override
    public void deleteMovieById(Long userId, Long movieId) {

        MovieEntity movieEntity = movieRepository.findById(movieId, userId)
            .orElseThrow(() -> new MovieNotFoundException("You don't have movie with this id " + movieId + " !"));

        movieRepository.delete(movieEntity);
    }

    @Override
    public Page<MovieCardDТО> findAll(Pageable pageable, Long userId, SearchParamsDTO searchParamsDTO) {

        if (movieRepository.findTotal(userId) == 0) {
            return null;
        }

        Page<MovieEntity> movies = movieRepository
            .findAllMoviesCard(pageable, userId,
                searchParamsDTO.getMinDuration() != null ? Optional.of(searchParamsDTO.getMinDuration()) : Optional.empty(),
                searchParamsDTO.getMaxDuration() != null ? Optional.of(searchParamsDTO.getMaxDuration()) : Optional.empty(),
                searchParamsDTO.getMinRating() != null ? Optional.of(searchParamsDTO.getMinRating()) : Optional.empty(),
                searchParamsDTO.getMaxRating() != null ? Optional.of(searchParamsDTO.getMaxRating()) : Optional.empty(),
                searchParamsDTO.getSearchText() != null ? Optional.of(searchParamsDTO.getSearchText()) : Optional.empty(),
                searchParamsDTO.getGenres() != null ? Optional.of(Arrays.stream(searchParamsDTO.getGenres()).toList()) : Optional.empty());

        return movies.map(m -> modelMapper.map(m, MovieCardDТО.class));
    }

    @Override
    @Transactional
    public MovieDTO getMovieDetailsDto(Long userId, Long movieId) {

        MovieEntity movieEntity = movieRepository.findById(movieId, userId)
            .orElseThrow(() -> new MovieNotFoundException("You don't have movie with this id " + movieId + " !"));

        return mapper.mapToResource(movieEntity);
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
    public StatisticsDТО getStatistics(Long userId) {
        StatisticsDТО statisticsDTO = new StatisticsDТО();

        statisticsDTO.setTotalMovies(movieRepository.findTotal(userId));
        statisticsDTO.setTotalDurations(movieRepository.findTotalDurations(userId));
        statisticsDTO.setActionMovies(movieRepository.countMoviesByGenre("ACTION", userId));
        statisticsDTO.setComedyMovies(movieRepository.countMoviesByGenre("COMEDY", userId));
        statisticsDTO.setDramaMovies(movieRepository.countMoviesByGenre("DRAMA", userId));
        statisticsDTO.setMysteryMovies(movieRepository.countMoviesByGenre("MYSTERY", userId));
        statisticsDTO.setSciFiMovies(movieRepository.countMoviesByGenre("SCI-FI", userId));
        statisticsDTO.setAnimationMovies(movieRepository.countMoviesByGenre("ANIMATION", userId));
        statisticsDTO.setAdventureMovies(movieRepository.countMoviesByGenre("ADVENTURE", userId));
        statisticsDTO.setFantasyMovies(movieRepository.countMoviesByGenre("FANTASY", userId));
        statisticsDTO.setRomanceMovies(movieRepository.countMoviesByGenre("ROMANCE", userId));
        statisticsDTO.setThrillerMovies(movieRepository.countMoviesByGenre("THRILLER", userId));
        statisticsDTO.setCrimeMovies(movieRepository.countMoviesByGenre("CRIME", userId));
        statisticsDTO.setCrimeMovies(movieRepository.countMoviesByGenre("BIOGRAPHY", userId));
        statisticsDTO.setDocumentaryMovies(movieRepository.countMoviesByGenre("DOCUMENTARY", userId));
        statisticsDTO.setSerialMovies(movieRepository.countMoviesByGenre("SERIAL", userId));

        return statisticsDTO;
    }

    private BigDecimal getIMDbRating(String IMDbURL) {

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
}
