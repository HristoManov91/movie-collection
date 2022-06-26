package com.example.moviecollectionbackend.repository;

import com.example.moviecollectionbackend.model.entity.MovieEntity;
import com.example.moviecollectionbackend.service.MovieService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long> {

    // @Query(value = " select * from `movie-collection`.movies as m join `movie-collection`.movies_genres as mg on m.id = mg.movie_entity_id join `movie-collection`.genres as g on g.id = mg.genres_id where m.duration between :minDuration and :maxDuration ",
    //    countQuery = " select count(distinct m.id) from `movie-collection`.movies as m join `movie-collection`.movies_genres as mg on m.id = mg.movie_entity_id join `movie-collection`.genres as g on g.id = mg.genres_id where m.duration between :minDuration and :maxDuration ",
    //    nativeQuery = true)
//    @EntityGraph("movie-card")
    @Query(value =
        " select * from `movie-collection`.movies as m "
            + " join `movie-collection`.movies_genres mg on m.id = mg.movie_entity_id "
            + " join `movie-collection`.genres g on g.id = mg.genres_id"
            + " where (m.duration is not null and m.duration between :minDuration and :maxDuration) "
            + " and (m.rating is not null and m.rating between :minRating and :maxRating) "
            + " and (m.title1 is not null and UPPER(m.title1) LIKE CONCAT('%', UPPER(:searchText) , '%') "
            + " or (m.title2 is not null and UPPER(m.title2) LIKE CONCAT('%', UPPER(:searchText) , '%'))) "
            + " and (COALESCE(:genres) is null or g.genre in (:genres)) "
            + " group by m.id",
        countQuery =
            " select count(m.id) from `movie-collection`.movies as m "
                + " join `movie-collection`.movies_genres mg on m.id = mg.movie_entity_id "
                + " join `movie-collection`.genres g on g.id = mg.genres_id"
                + " where (m.duration is not null and m.duration between :minDuration and :maxDuration) "
                + " and (m.rating is not null and m.rating between :minRating and :maxRating) "
                + " and (m.title1 is not null and UPPER(m.title1) LIKE CONCAT('%', UPPER(:searchText) , '%') "
                + " or (m.title2 is not null and UPPER(m.title2) LIKE CONCAT('%', UPPER(:searchText) , '%'))) "
                + " and (COALESCE(:genres) is null or g.genre in (:genres)) "
                + " group by m.id",
        nativeQuery = true)
    Page<MovieEntity> findAllMoviesCard(
        Pageable pageable,
        @Param("minDuration") Optional<Integer> minDuration,
        @Param("maxDuration") Optional<Integer> maxDuration,
        @Param("minRating") Optional<Number> minRating,
        @Param("maxRating") Optional<Number> maxRating,
        @Param("searchText") Optional<String> searchText,
        @Param("genres") Optional<List<String>> genres);

    @Query("SELECT COUNT(m.id) FROM MovieEntity m")
    Integer findTotal();

    @Query("SELECT SUM(m.duration) FROM MovieEntity m")
    Integer findTotalDurations();

    @Query(value = "SELECT COUNT(mg.movie_entity_id) "
        + "FROM `movie-collection`.movies_genres as mg "
        + "left join `movie-collection`.genres as g on g.id = mg.genres_id "
        + "where g.genre = ?1",
        nativeQuery = true)
    Integer countMoviesByGenre(String genre);

//    @Query("SELECT COUNT(m.id) FROM MovieEntity m where m.ge")
//    Integer findTotalMoviesByGenres(String action);

//    @Query(value = "SELECT g.genre , COUNT(mg.movie_entity_id) "
//        + "FROM `movie-collection`.genres g left join `movie-collection`.movies_genres as mg on g.id = mg.genres_id "
//        + "group by mg.genres_id",
//        countQuery = "SELECT COUNT(*) FROM `movie-collection`.genres",
//        nativeQuery = true)
//    Map<String , Integer> countAllByGenres();

//    @Query("select count(m.id) from MovieEntity m where ?1 IN (m.genres.genre)")
//    Integer countMoviesByGenres(String genre);
}
