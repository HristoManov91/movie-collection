package com.example.moviecollectionbackend.service.impl;

import com.example.moviecollectionbackend.model.entity.GenreEntity;
import com.example.moviecollectionbackend.repository.GenreRepository;
import com.example.moviecollectionbackend.service.GenreService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public List<String> findAllByMovieId(Long movieId) {
        List<GenreEntity> allByMovieId = genreRepository.findAllByMovieId(movieId);
        return allByMovieId.stream().map(GenreEntity::getGenre).collect(Collectors.toList());
    }

    public List<GenreEntity> findAllByNames(List<String> genresNames) {
        return genresNames
            .stream()
            .map(genreRepository::findByGenre)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllGenres() {
        return genreRepository.findAll().stream().map(GenreEntity::getGenre).collect(Collectors.toList());
    }
}
