package com.example.moviecollectionbackend.service;

import com.example.moviecollectionbackend.model.dto.MovieDTO;
import com.example.moviecollectionbackend.model.dto.MovieCardDТО;
import com.example.moviecollectionbackend.model.dto.SearchParamsDTO;
import com.example.moviecollectionbackend.model.dto.StatisticsDТО;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MovieService {

    /**
     * Finds brief information about all movies.
     * @param pageable Specifies which and how many elements to return as a result.
     * @param userId Filter movies on user witch this id.
     * @param searchParamsDTO An object with all the parameters to filter movies.
     * @return Page with MovieCardDТО.
     */
    Page<MovieCardDТО> findAll(Pageable pageable, Long userId, SearchParamsDTO searchParamsDTO);

    /**
     * Register User from userRegisterDTO.
     * @param userId To check if the requested movie is owned by user with this user id.
     * @param movieId This is requested movie data with this movie id.
     * @return MovieDTO.
     */
    MovieDTO getMovieDetailsDto(Long userId, Long movieId);

    /**
     * Adds a movie to a user with this user id.
     * @param userId The user to whom the movie will belong.
     * @param movieDTO An object with all movie data.
     * @return MovieDTO.
     */
    MovieDTO addMovie(Long userId, MovieDTO movieDTO);

    /**
     * Edit a movie to a user with this user id.
     * @param userId To check if the edited movie is owned by user with this user id.
     * @param movieDTO An object with all new movie data.
     * @return MovieDTO.
     */
    MovieDTO editMovie(Long userId, MovieDTO movieDTO);

    /**
     * Delete movie by id to a user with this user id.
     * @param userId To check if the edited movie is owned by user with this user id.
     * @param movieId The ID of the movie to be deleted.
     */
    void deleteMovieById(Long userId, Long movieId);

    /**
     * Scheduler task for update all movies IMDb ratings.
     */
    void updateRatings();

    /**
     * Gets statistics for all movies on user.
     * @param userId .
     * @return StatisticsDТО.
     */
    StatisticsDТО getStatistics(Long userId);
}

