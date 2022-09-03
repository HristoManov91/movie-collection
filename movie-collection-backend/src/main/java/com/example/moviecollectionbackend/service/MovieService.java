package com.example.moviecollectionbackend.service;

import com.example.moviecollectionbackend.model.dto.MovieDTO;
import com.example.moviecollectionbackend.model.dto.MovieCardDТО;
import com.example.moviecollectionbackend.model.dto.SearchParamsDTO;
import com.example.moviecollectionbackend.model.dto.StatisticsDТО;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MovieService {

    Page<MovieCardDТО> findAll(Pageable pageable, Long userId, SearchParamsDTO searchParamsDTO);

    MovieDTO getMovieDetailsDto(Long userId, Long movieId);

    MovieDTO addMovie(Long userId, MovieDTO movieDTO);

    MovieDTO editMovie(Long userId, MovieDTO movieDTO);

    void deleteMovieById(Long userId, Long movieId);

    void updateRatings();

    StatisticsDТО getStatistics(Long userId);
}

