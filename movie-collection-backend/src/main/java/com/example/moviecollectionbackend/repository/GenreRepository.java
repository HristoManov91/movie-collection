package com.example.moviecollectionbackend.repository;

import com.example.moviecollectionbackend.model.entity.GenreEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<GenreEntity, Long> {

    @Query(value = "SELECT * FROM genres LEFT JOIN movies_genres mg on genres.id = mg.genres_id WHERE mg.movie_entity_id = :movieId" , nativeQuery = true)
    List<GenreEntity> findAllByMovieId(@Param("movieId") Long movieId);
}
