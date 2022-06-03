package com.example.moviecollectionbackend.service;

import com.example.moviecollectionbackend.model.entity.GenreEntity;
import java.util.List;

public interface GenreService {

    List<String> findAllByMovieId(Long movieId);

    List<GenreEntity> findAllByNames (List<String> genres);

    List<String> findAllGenres();
}
