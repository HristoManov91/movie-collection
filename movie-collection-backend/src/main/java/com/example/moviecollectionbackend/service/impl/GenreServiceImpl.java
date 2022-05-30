package com.example.moviecollectionbackend.service.impl;

import com.example.moviecollectionbackend.repository.GenreRepository;
import com.example.moviecollectionbackend.service.GenreService;
import org.springframework.stereotype.Service;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }
}
