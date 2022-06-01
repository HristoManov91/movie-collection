package com.example.moviecollectionbackend.service;

import com.example.moviecollectionbackend.model.binding.AddMovieBindingModel;
import com.example.moviecollectionbackend.model.dto.MovieCardDto;
import com.example.moviecollectionbackend.model.dto.MovieDetailsDto;
import java.util.List;
import javax.management.InvalidApplicationException;

public interface MovieService {

    List<MovieCardDto> findAllMovies();

    MovieDetailsDto getMovieDetailsDto(Long movieId) throws InvalidApplicationException;

    MovieDetailsDto addMovie(AddMovieBindingModel addMovieBindingModel);
}
