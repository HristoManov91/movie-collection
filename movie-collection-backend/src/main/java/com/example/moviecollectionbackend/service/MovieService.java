package com.example.moviecollectionbackend.service;

import com.example.moviecollectionbackend.model.dto.AddMovieDTO;
import com.example.moviecollectionbackend.model.dto.EditMovieDTO;
import com.example.moviecollectionbackend.model.dto.MovieCardDto;
import com.example.moviecollectionbackend.model.dto.MovieDetailsDto;
import com.example.moviecollectionbackend.model.dto.SearchParamsDTO;
import com.example.moviecollectionbackend.model.dto.StatisticsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MovieService {

    Page<MovieCardDto> findAll(Pageable pageable, Long userId, SearchParamsDTO searchParamsDTO);

    MovieDetailsDto getMovieDetailsDto(Long userId, Long movieId);

    MovieDetailsDto addMovie(Long userId, AddMovieDTO addMovieDTO);

    MovieDetailsDto editMovie(Long userId, EditMovieDTO editMovieDTO);

    void deleteMovieById(Long userId, Long movieId);

    void updateRatings();

    StatisticsDto getStatistics(Long userId);
}

