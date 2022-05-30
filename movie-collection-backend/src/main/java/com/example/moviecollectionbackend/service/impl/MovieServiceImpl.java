package com.example.moviecollectionbackend.service.impl;

import com.example.moviecollectionbackend.repository.MovieRepository;
import com.example.moviecollectionbackend.service.MovieService;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
}
