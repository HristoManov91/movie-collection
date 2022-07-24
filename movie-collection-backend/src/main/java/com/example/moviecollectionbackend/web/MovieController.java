package com.example.moviecollectionbackend.web;

import com.example.moviecollectionbackend.exception.FullMovieCollectionException;
import com.example.moviecollectionbackend.exception.InvalidIMDbUrlException;
import com.example.moviecollectionbackend.exception.MovieNotFoundException;
import com.example.moviecollectionbackend.exception.UserNotFoundException;
import com.example.moviecollectionbackend.model.dto.AddMovieDTO;
import com.example.moviecollectionbackend.model.dto.EditMovieDTO;
import com.example.moviecollectionbackend.model.dto.MovieCardDto;
import com.example.moviecollectionbackend.model.dto.MovieDetailsDto;
import com.example.moviecollectionbackend.model.dto.StatisticsDto;
import com.example.moviecollectionbackend.model.user.AppUserDetails;
import com.example.moviecollectionbackend.service.MovieService;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    private ResponseEntity<MovieDetailsDto> addMovie(
        @AuthenticationPrincipal AppUserDetails userDetails, @RequestBody @Valid AddMovieDTO addMovieDTO)
        throws InvalidIMDbUrlException, UserNotFoundException, FullMovieCollectionException {

        Long userId = userDetails.getId();
        return new ResponseEntity<>(movieService.addMovie(userId, addMovieDTO), HttpStatus.CREATED);
    }

    @PostMapping("/all")
//    @PreAuthorize("hasRole('USER')")
    private ResponseEntity<Page<MovieCardDto>> findAllWithPagination(
        Pageable pageable, @AuthenticationPrincipal AppUserDetails userDetails, @RequestBody Map<String, Object> params) {

        Long userId = userDetails.getId();

        Page<MovieCardDto> allMoviesWithPagination = movieService.findAllMoviesWithPagination(pageable, userId, params);

        return ResponseEntity.ok(allMoviesWithPagination);
    }

    @GetMapping("/{movieId}")
//    @PreAuthorize("hasRole('USER')")
    private ResponseEntity<MovieDetailsDto> getMovieDetailsDto(
        @AuthenticationPrincipal AppUserDetails userDetails, @PathVariable Long movieId)
        throws MovieNotFoundException {
        Long userId = userDetails.getId();

        return new ResponseEntity<>(movieService.getMovieDetailsDto(userId, movieId), HttpStatus.OK);
    }

    @PostMapping("/edit")
//    @PreAuthorize("hasRole('USER')")
    private ResponseEntity<MovieDetailsDto> editMovie(
        @AuthenticationPrincipal AppUserDetails userDetails, @RequestBody @Valid EditMovieDTO editMovieDTO)
        throws MovieNotFoundException {

        Long userId = userDetails.getId();

        MovieDetailsDto movieDetailsDto = movieService.editMovie(userId, editMovieDTO);

        return new ResponseEntity<>(movieDetailsDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
//    @PreAuthorize("hasRole('USER')")
    private ResponseEntity<?> deleteMovie(
        @AuthenticationPrincipal AppUserDetails userDetails, @RequestParam(name = "movieId") Long movieId)
        throws MovieNotFoundException {

        Long userId = userDetails.getId();

        this.movieService.deleteMovieById(userId , movieId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/statistics")
//    @PreAuthorize("hasRole('USER')")
    private ResponseEntity<StatisticsDto> getStatistics(@AuthenticationPrincipal AppUserDetails userDetails) {
        Long userId = userDetails.getId();

        return new ResponseEntity<>(this.movieService.getStatistics(userId), HttpStatus.OK);
    }
}
