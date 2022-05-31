package com.example.moviecollectionbackend.web;

import com.example.moviecollectionbackend.model.dto.MovieCardDto;
import com.example.moviecollectionbackend.service.MovieService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/all")
    private ResponseEntity<List<MovieCardDto>> findAllMovies(){
        List<MovieCardDto> allMovies = movieService.findAllMovies();
        return ResponseEntity.ok(allMovies);
    }
}
