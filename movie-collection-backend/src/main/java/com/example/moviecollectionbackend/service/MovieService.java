package com.example.moviecollectionbackend.service;

import com.example.moviecollectionbackend.exception.InvalidIMDbUrlException;
import com.example.moviecollectionbackend.exception.MovieNotFoundException;
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

    Page<MovieCardDto> findAllMoviesWithPagination(Pageable pageable , Map<String , Object> params);

    MovieDetailsDto getMovieDetailsDto(Long movieId) throws MovieNotFoundException;

    MovieDetailsDto addMovie(AddMovieDTO addMovieDTO) throws InvalidIMDbUrlException;

    MovieDetailsDto editMovie(EditMovieDTO editMovieDTO);

    Boolean deleteMovieById(Long movieId);

    void updateRatings() throws URISyntaxException, InvalidIMDbUrlException;

    StatisticsDto getStatistics();
}

