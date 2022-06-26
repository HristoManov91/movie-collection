package com.example.moviecollectionbackend.web;

import com.example.moviecollectionbackend.exception.UserNotFoundException;
import com.example.moviecollectionbackend.model.binding.AddMovieBindingModel;
import com.example.moviecollectionbackend.model.binding.EditMovieBindingModel;
import com.example.moviecollectionbackend.model.dto.MovieCardDto;
import com.example.moviecollectionbackend.model.dto.MovieDetailsDto;
import com.example.moviecollectionbackend.model.dto.StatisticsDto;
import com.example.moviecollectionbackend.model.entity.MovieEntity;
import com.example.moviecollectionbackend.service.MovieService;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
@CrossOrigin(origins = "http://localhost:8080/")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }


    @PostMapping("/new")
    private ResponseEntity<MovieDetailsDto> addMovie (@RequestBody @Valid AddMovieBindingModel addMovieBindingModel) throws URISyntaxException {
        return new ResponseEntity<>(movieService.addMovie(addMovieBindingModel), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    private ResponseEntity<List<MovieCardDto>> findAllMovies(){
        List<MovieCardDto> allMovies = movieService.findAllMovies();
        return ResponseEntity.ok(allMovies);
    }

    @PostMapping("/all")
    private ResponseEntity<Page<MovieCardDto>> findAllWithPagination (Pageable pageable , @RequestBody Map<String , Object> params){
        Page<MovieCardDto> allMoviesWithPagination = movieService.findAllMoviesWithPagination(pageable , params);

        return ResponseEntity.ok(allMoviesWithPagination);
    }

    @GetMapping("/{movieId}")
    private ResponseEntity<MovieDetailsDto> getMovieDetailsDto(@PathVariable Long movieId) throws UserNotFoundException {
        return new ResponseEntity<>(movieService.getMovieDetailsDto(movieId), HttpStatus.OK);
    }

    @PostMapping("/edit")
    private ResponseEntity<MovieDetailsDto> editMovie (@RequestBody @Valid EditMovieBindingModel editMovieBindingModel) {
        return new ResponseEntity<>(movieService.editMovie(editMovieBindingModel) , HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    private ResponseEntity<Boolean> deleteMovie(@RequestParam(name = "movieId") Long movieId){
        return new ResponseEntity<>(this.movieService.deleteMovieById(movieId) , HttpStatus.OK);
    }

    @GetMapping("/statistics")
    private ResponseEntity<StatisticsDto> getStatistics(){
        return new ResponseEntity<>(this.movieService.getStatistics() , HttpStatus.OK);
    }
}
