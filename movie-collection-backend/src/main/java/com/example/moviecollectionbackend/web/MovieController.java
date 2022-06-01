package com.example.moviecollectionbackend.web;

import com.example.moviecollectionbackend.model.binding.AddMovieBindingModel;
import com.example.moviecollectionbackend.model.dto.MovieCardDto;
import com.example.moviecollectionbackend.model.dto.MovieDetailsDto;
import com.example.moviecollectionbackend.service.MovieService;
import java.util.List;
import javax.management.InvalidApplicationException;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }


    @PostMapping("/new")
    private ResponseEntity<MovieDetailsDto> addMovie (@RequestBody @Valid AddMovieBindingModel addMovieBindingModel){
        movieService.addMovie(addMovieBindingModel);
        return null;
    }

    @GetMapping("/all")
    private ResponseEntity<List<MovieCardDto>> findAllMovies(){
        List<MovieCardDto> allMovies = movieService.findAllMovies();
        return ResponseEntity.ok(allMovies);
    }

    @GetMapping("/{movieId}")
    private ResponseEntity<MovieDetailsDto> getMovieDetailsDto(@PathVariable Long movieId) throws InvalidApplicationException {

        return new ResponseEntity<>(movieService.getMovieDetailsDto(movieId), HttpStatus.OK);
    }
}
