package com.example.moviecollectionbackend.service;

import com.example.moviecollectionbackend.model.entity.PlatformEntity;
import java.util.List;

public interface PlatformService {

    /**
     * Find all platforms names by movie id.
     * @param movieId This is the movie id.
     * @return List of Strings with all platforms found.
     */
    List<String> findAllByMovieId(Long movieId);

    /**
     * Find all PlatformEntity by a List of Strings representing their names.
     * @param platforms List of Strings.
     * @return List of PlatformEntity.
     */
    List<PlatformEntity> findAllByNames(List<String> platforms);

    /**
     * Find all platforms names.
     * @return List of Strings with all platforms names.
     */
    List<String> findAllPlatformsNames();
}
