package com.example.moviecollectionbackend.service;

import com.example.moviecollectionbackend.exception.FullMovieCollectionException;
import com.example.moviecollectionbackend.exception.InvalidIMDbUrlException;
import com.example.moviecollectionbackend.exception.MovieNotFoundException;
import com.example.moviecollectionbackend.exception.UserNotFoundException;
import com.example.moviecollectionbackend.model.dto.AddMovieDTO;
import com.example.moviecollectionbackend.model.dto.EditMovieDTO;
import com.example.moviecollectionbackend.model.dto.MovieCardDto;
import com.example.moviecollectionbackend.model.dto.MovieDetailsDto;
import com.example.moviecollectionbackend.model.dto.StatisticsDto;
import java.net.URISyntaxException;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MovieService {

    Page<MovieCardDto> findAllMoviesWithPagination(Pageable pageable , Long userId , Map<String , Object> params);

    MovieDetailsDto getMovieDetailsDto(Long userId , Long movieId) throws MovieNotFoundException;

    MovieDetailsDto addMovie(Long userId , AddMovieDTO addMovieDTO) throws InvalidIMDbUrlException, UserNotFoundException, FullMovieCollectionException;

    MovieDetailsDto editMovie(Long userId , EditMovieDTO editMovieDTO) throws MovieNotFoundException;

    void deleteMovieById(Long userId , Long movieId) throws MovieNotFoundException;

    void updateRatings() throws URISyntaxException, InvalidIMDbUrlException;

    StatisticsDto getStatistics(Long userId);
}

