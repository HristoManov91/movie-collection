package com.example.moviecollectionbackend.service.impl;

import com.example.moviecollectionbackend.model.binding.AddMovieBindingModel;
import com.example.moviecollectionbackend.model.dto.MovieCardDto;
import com.example.moviecollectionbackend.model.dto.MovieDetailsDto;
import com.example.moviecollectionbackend.model.entity.MovieEntity;
import com.example.moviecollectionbackend.repository.MovieRepository;
import com.example.moviecollectionbackend.service.GenreService;
import com.example.moviecollectionbackend.service.MovieService;
import com.example.moviecollectionbackend.service.PlatformService;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper;
    private final GenreService genreService;
    private final PlatformService platformService;

    public MovieServiceImpl(MovieRepository movieRepository, ModelMapper modelMapper, GenreService genreService, PlatformService platformService) {
        this.movieRepository = movieRepository;
        this.modelMapper = modelMapper;
        this.genreService = genreService;
        this.platformService = platformService;
    }

    @Override
    public MovieDetailsDto addMovie(AddMovieBindingModel addMovieBindingModel) {
        MovieEntity map = modelMapper.map(addMovieBindingModel, MovieEntity.class);

        MovieEntity movieEntity = mapDtoToEntity(addMovieBindingModel);
        return null;
    }
    @Override
    public List<MovieCardDto> findAllMovies() {
        List<MovieEntity> allMovies = movieRepository.findAll();

        return allMovies.stream().map(m -> modelMapper.map(m, MovieCardDto.class)).toList();
    }

    @Override
    public MovieDetailsDto getMovieDetailsDto(Long movieId) {
        MovieEntity movieEntity = movieRepository.findById(movieId).orElseThrow();
        MovieDetailsDto movieDetailsDto = mapEntityToDto(movieEntity);

        if (movieDetailsDto == null){
            throw new IllegalArgumentException();
        }

        List<String> genres = genreService.findAllByMovieId(movieId);
        movieDetailsDto.setGenres(genres);

        List<String> platforms = platformService.findAllByMovieId(movieId);
        movieDetailsDto.setPlatforms(platforms);

        return movieDetailsDto;
    }

    private MovieDetailsDto mapEntityToDto (MovieEntity me){
        return new MovieDetailsDto()
            .setTitle1(me.getTitle1() != null ? me.getTitle1() : null)
            .setTitle2(me.getTitle2() != null ? me.getTitle2() : null)
            .setDuration(me.getDuration() != null ? me.getDuration() : null)
            .setDescription(me.getDescription() != null ? me.getDescription() : null)
            .setBulgarianLanguage(me.getBulgarianLanguage() != null ? me.getBulgarianLanguage() : null)
            .setRating(me.getRating() != null ? me.getRating() : null)
            .setTrailerUrl(me.getTrailerUrl() != null ? me.getTrailerUrl() : null)
            .setPosterUrl(me.getPosterUrl() != null ? me.getPosterUrl() : null)
            .setImdbUrl(me.getImdbUrl() != null ? me.getImdbUrl() : null)
            .setYear(me.getYear() != null ? me.getYear() : null);

    }

    private MovieEntity mapDtoToEntity (AddMovieBindingModel bindingModel){
        return new MovieEntity()
            .setTitle1(bindingModel.getTitle1() != null ? bindingModel.getTitle1() : null)
            .setTitle2(bindingModel.getTitle2() != null ? bindingModel.getTitle2() : null)
            .setDuration(bindingModel.getDuration() != null ? bindingModel.getDuration() : null)
            .setDescription(bindingModel.getDescription() != null ? bindingModel.getDescription() : null)
            .setBulgarianLanguage(bindingModel.getBulgarianLanguage() != null ? bindingModel.getBulgarianLanguage() : null)
            .setTrailerUrl(bindingModel.getTrailerUrl() != null ? bindingModel.getTrailerUrl() : null)
            .setPosterUrl(bindingModel.getPosterUrl() != null ? bindingModel.getPosterUrl() : null)
            .setImdbUrl(bindingModel.getImdbUrl() != null ? bindingModel.getImdbUrl() : null)
            .setYear(bindingModel.getYear() != null ? bindingModel.getYear() : null);
    }
}
