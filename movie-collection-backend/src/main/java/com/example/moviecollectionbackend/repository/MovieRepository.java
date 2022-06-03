package com.example.moviecollectionbackend.repository;

import com.example.moviecollectionbackend.model.dto.MovieCardDto;
import com.example.moviecollectionbackend.model.entity.MovieEntity;
import java.util.List;
import java.util.Optional;
import javax.persistence.NamedEntityGraph;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long> {

    @EntityGraph("movie-card")
    @Query("SELECT m FROM MovieEntity m")
    List<MovieEntity> findAllMoviesCard();

}
