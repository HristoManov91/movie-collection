package com.example.moviecollectionbackend.service;

import java.util.List;

public interface PlatformService {

    List<String> findAllByMovieId(Long movieId);
}
