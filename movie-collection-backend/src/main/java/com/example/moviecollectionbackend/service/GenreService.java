package com.example.moviecollectionbackend.service;

import java.util.List;

public interface GenreService {

    List<String> findAllByMovieId(Long movieId);
}
