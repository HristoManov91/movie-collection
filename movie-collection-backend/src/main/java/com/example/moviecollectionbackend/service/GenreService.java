package com.example.moviecollectionbackend.service;

import com.example.moviecollectionbackend.model.entity.GenreEntity;
import java.util.List;

public interface GenreService {

    /**
     * Find all genres names by movie id.
     * @param movieId This is the movie id.
     * @return List of Strings with all genres found.
     */
    List<String> findAllByMovieId(Long movieId);

    /**
     * Find all GenresEntity by a List of Strings representing their names.
     * @param genres List of Strings.
     * @return List of GenreEntity.
     */
    List<GenreEntity> findAllByNames (List<String> genres);

    /**
     * Find all genres names.
     * @return List of Strings with all genres names.
     */
    List<String> findAllGenres();
}
