package com.example.moviecollectionbackend.service;

import com.example.moviecollectionbackend.model.entity.PlatformEntity;
import java.util.List;

public interface PlatformService {

    List<String> findAllByMovieId(Long movieId);

    List<PlatformEntity> findAllByNames(List<String> platforms);

    List<String> findAllPlatformsNames();
}
