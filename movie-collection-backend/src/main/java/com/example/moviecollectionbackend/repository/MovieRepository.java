package com.example.moviecollectionbackend.repository;

import com.example.moviecollectionbackend.model.dto.MovieCardDto;
import com.example.moviecollectionbackend.model.entity.MovieEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long> {

    @Query("SELECT m.title1 , m.title2 , m.rating , m.duration , m.id , m.trailerUrl FROM MovieEntity as m")
    Optional<List<MovieCardDto>> findAllMovies();
}
