package com.example.moviecollectionbackend.web;

import com.example.moviecollectionbackend.exception.MovieNotFoundException;
import com.example.moviecollectionbackend.model.dto.AddMovieDTO;
import com.example.moviecollectionbackend.model.dto.EditMovieDTO;
import com.example.moviecollectionbackend.model.dto.MovieCardDto;
import com.example.moviecollectionbackend.model.dto.MovieDetailsDto;
import com.example.moviecollectionbackend.model.dto.StatisticsDto;
import com.example.moviecollectionbackend.service.MovieService;
import java.net.URISyntaxException;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
//    @PreAuthorize("hasRole('USER')")
    private ResponseEntity<MovieDetailsDto> addMovie (@RequestBody @Valid AddMovieDTO addMovieDTO) throws URISyntaxException {
        return new ResponseEntity<>(movieService.addMovie(addMovieDTO), HttpStatus.CREATED);
    }

    @PostMapping("/all")
//    @PreAuthorize("hasRole('USER')")
    private ResponseEntity<Page<MovieCardDto>> findAllWithPagination (Pageable pageable , @RequestBody Map<String , Object> params){
        Page<MovieCardDto> allMoviesWithPagination = movieService.findAllMoviesWithPagination(pageable , params);

        return ResponseEntity.ok(allMoviesWithPagination);
    }

    @GetMapping("/{movieId}")
//    @PreAuthorize("hasRole('USER')")
    private ResponseEntity<MovieDetailsDto> getMovieDetailsDto(@PathVariable Long movieId) throws MovieNotFoundException {
        return new ResponseEntity<>(movieService.getMovieDetailsDto(movieId), HttpStatus.OK);
    }

    @PostMapping("/edit")
//    @PreAuthorize("hasRole('USER')")
    private ResponseEntity<MovieDetailsDto> editMovie (@RequestBody @Valid EditMovieDTO editMovieDTO) {
        return new ResponseEntity<>(movieService.editMovie(editMovieDTO) , HttpStatus.OK);
    }

    @DeleteMapping("/delete")
//    @PreAuthorize("hasRole('USER')")
    private ResponseEntity<Boolean> deleteMovie(@RequestParam(name = "movieId") Long movieId) {

        Boolean result = this.movieService.deleteMovieById(movieId);
        return new ResponseEntity<>( result , result ? HttpStatus.OK : HttpStatus.BAD_REQUEST );
    }

    @GetMapping("/statistics")
//    @PreAuthorize("hasRole('USER')")
    private ResponseEntity<StatisticsDto> getStatistics(){
        return new ResponseEntity<>(this.movieService.getStatistics() , HttpStatus.OK);
    }
}
