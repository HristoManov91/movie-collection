package com.example.moviecollectionbackend.service;

import com.example.moviecollectionbackend.exception.UserNotFoundException;
import com.example.moviecollectionbackend.model.binding.AddMovieBindingModel;
import com.example.moviecollectionbackend.model.binding.EditMovieBindingModel;
import com.example.moviecollectionbackend.model.dto.MovieCardDto;
import com.example.moviecollectionbackend.model.dto.MovieDetailsDto;
import com.example.moviecollectionbackend.model.dto.StatisticsDto;
import com.example.moviecollectionbackend.model.entity.MovieEntity;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MovieService {

    Page<MovieCardDto> findAllMoviesWithPagination(Pageable pageable , Map<String , Object> params);

    MovieDetailsDto getMovieDetailsDto(Long movieId) throws UserNotFoundException;

    MovieDetailsDto addMovie(AddMovieBindingModel addMovieBindingModel) throws URISyntaxException;

    MovieDetailsDto editMovie(EditMovieBindingModel editMovieBindingModel);

    Boolean deleteMovieById(Long movieId);

    void updateRatings() throws URISyntaxException;

    StatisticsDto getStatistics();
}

