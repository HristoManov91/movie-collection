package com.example.moviecollectionbackend.repository;

import com.example.moviecollectionbackend.model.dto.MovieCardDto;
import com.example.moviecollectionbackend.model.entity.MovieEntity;
import com.example.moviecollectionbackend.service.MovieService;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long> {

    @EntityGraph("movie-card")
    @Query("SELECT m FROM MovieEntity m")
    Page<MovieEntity> findAllMoviesCard(Pageable pageable);

    // private Long id;
    //    private String title1;
    //    private String title2;
    //    private String trailerUrl;
    //    private Integer duration;
    //    private BigDecimal rating;
    //    private String posterUrl;
    //    private List<String> genres;
}
