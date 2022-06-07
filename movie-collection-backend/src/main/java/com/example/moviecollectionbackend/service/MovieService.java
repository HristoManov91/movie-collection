package com.example.moviecollectionbackend.service;

import com.example.moviecollectionbackend.exception.UserNotFoundException;
import com.example.moviecollectionbackend.model.binding.AddMovieBindingModel;
import com.example.moviecollectionbackend.model.binding.EditMovieBindingModel;
import com.example.moviecollectionbackend.model.dto.MovieCardDto;
import com.example.moviecollectionbackend.model.dto.MovieDetailsDto;
import java.util.List;

public interface MovieService {

    List<MovieCardDto> findAllMovies();

    MovieDetailsDto getMovieDetailsDto(Long movieId) throws UserNotFoundException;

    MovieDetailsDto addMovie(AddMovieBindingModel addMovieBindingModel);

    MovieDetailsDto editMovie(EditMovieBindingModel editMovieBindingModel);
}

