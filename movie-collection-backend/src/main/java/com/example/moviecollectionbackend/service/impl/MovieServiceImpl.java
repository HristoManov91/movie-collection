package com.example.moviecollectionbackend.service.impl;

import com.example.moviecollectionbackend.model.dto.MovieCardDto;
import com.example.moviecollectionbackend.model.entity.MovieEntity;
import com.example.moviecollectionbackend.repository.MovieRepository;
import com.example.moviecollectionbackend.service.MovieService;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper;

    public MovieServiceImpl(MovieRepository movieRepository, ModelMapper modelMapper) {
        this.movieRepository = movieRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<MovieCardDto> findAllMovies() {
//        List<MovieCardDto> allMovies = movieRepository.findAllMovies().orElseThrow(ObjectCollectedException::new);
        List<MovieEntity> all = movieRepository.findAll();
        List<MovieCardDto> collect = all.stream().map(m -> modelMapper.map(m, MovieCardDto.class)).collect(Collectors.toList());
        return collect;
    }
}
