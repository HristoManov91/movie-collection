package com.example.moviecollectionbackend.service;

import com.example.moviecollectionbackend.model.dto.MovieCardDto;
import java.util.List;

public interface MovieService {

    List<MovieCardDto> findAllMovies();
}
