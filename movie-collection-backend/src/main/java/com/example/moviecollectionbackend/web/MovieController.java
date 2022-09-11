package com.example.moviecollectionbackend.web;

import com.example.moviecollectionbackend.model.dto.MovieCardDТО;
import com.example.moviecollectionbackend.model.dto.MovieDTO;
import com.example.moviecollectionbackend.model.dto.SearchParamsDTO;
import com.example.moviecollectionbackend.model.dto.StatisticsDТО;
import com.example.moviecollectionbackend.model.user.AppUserDetails;
import com.example.moviecollectionbackend.service.MovieService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    public ResponseEntity<MovieDTO> addMovie(
        @AuthenticationPrincipal AppUserDetails userDetails,
        @RequestBody @Valid MovieDTO movieDTO) {

        Long userId = userDetails.getId();
        return new ResponseEntity<>(movieService.addMovie(userId, movieDTO), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<MovieCardDТО>> findAll(@AuthenticationPrincipal AppUserDetails userDetails, @Valid SearchParamsDTO searchParamsDTO,
        Pageable pageable) {
        Long userId = userDetails.getId();

        Page<MovieCardDТО> result = movieService.findAll(pageable, userId, searchParamsDTO);

        return ResponseEntity.ok(result);
    }

    @Tag(name = "Get movie by ID", description = "Returns the movie info by ID")
    @Parameter(
        name = "movieId",
        description = "The ID of the Movie",
        required = true
    )
    @ApiResponse(
        responseCode = "200",
        description = "If the book was retrieved successfully"
    )
    @ApiResponse(
        responseCode = "404",
        description = "If the movie not found"
    )
    @GetMapping("/{movieId}")
    public ResponseEntity<MovieDTO> getMovieDetailsDto(
        @AuthenticationPrincipal AppUserDetails userDetails, @PathVariable Long movieId) {
        Long userId = userDetails.getId();

        return new ResponseEntity<>(movieService.getMovieDetailsDto(userId, movieId), HttpStatus.OK);
    }

    @PostMapping("/edit")
    public ResponseEntity<MovieDTO> editMovie(
        @AuthenticationPrincipal AppUserDetails userDetails, @RequestBody @Valid MovieDTO movieDTO) {

        Long userId = userDetails.getId();

        MovieDTO movieDetailsDto = movieService.editMovie(userId, movieDTO);

        return new ResponseEntity<>(movieDetailsDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteMovie(
        @AuthenticationPrincipal AppUserDetails userDetails, @RequestParam(name = "movieId") Long movieId) {

        Long userId = userDetails.getId();

        this.movieService.deleteMovieById(userId, movieId);

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping("/statistics")
    public ResponseEntity<StatisticsDТО> getStatistics(@AuthenticationPrincipal AppUserDetails userDetails) {
        Long userId = userDetails.getId();

        return new ResponseEntity<>(this.movieService.getStatistics(userId), HttpStatus.OK);
    }
}