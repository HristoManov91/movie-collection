package com.example.moviecollectionbackend.repository;

import com.example.moviecollectionbackend.model.entity.MovieEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long> {

    @Query("SELECT m FROM MovieEntity m where m.id = ?1 and m.user.id = ?2")
    Optional<MovieEntity> findById(Long movieId , Long userId);

    @Query(value =
        " select * from `movie-collection`.movies as m "
            + " join `movie-collection`.movies_genres mg on m.id = mg.movie_entity_id "
            + " join `movie-collection`.genres g on g.id = mg.genres_id"
            + " where (m.user_id = :userId)"
            + " and (m.duration is not null and m.duration between :minDuration and :maxDuration) "
            + " and (m.rating is not null and m.rating between :minRating and :maxRating) "
            + " and (m.title1 is not null and UPPER(m.title1) LIKE CONCAT('%', UPPER(:searchText) , '%') "
            + " or (m.title2 is not null and UPPER(m.title2) LIKE CONCAT('%', UPPER(:searchText) , '%'))) "
            + " and (COALESCE(:genres) is null or g.genre in (:genres)) "
            + " group by m.id",
        countQuery =
            " select count(m.id) from `movie-collection`.movies as m "
                + " join `movie-collection`.movies_genres mg on m.id = mg.movie_entity_id "
                + " join `movie-collection`.genres g on g.id = mg.genres_id"
                + " where (m.user_id = :userId)"
                + " and (m.duration is not null and m.duration between :minDuration and :maxDuration) "
                + " and (m.rating is not null and m.rating between :minRating and :maxRating) "
                + " and (m.title1 is not null and UPPER(m.title1) LIKE CONCAT('%', UPPER(:searchText) , '%') "
                + " or (m.title2 is not null and UPPER(m.title2) LIKE CONCAT('%', UPPER(:searchText) , '%'))) "
                + " and (COALESCE(:genres) is null or g.genre in (:genres)) "
                + " group by m.id",
        nativeQuery = true)
    Page<MovieEntity> findAllMoviesCard(
        Pageable pageable,
        @Param("userId") Long usesId,
        @Param("minDuration") Optional<Integer> minDuration,
        @Param("maxDuration") Optional<Integer> maxDuration,
        @Param("minRating") Optional<Number> minRating,
        @Param("maxRating") Optional<Number> maxRating,
        @Param("searchText") Optional<String> searchText,
        @Param("genres") Optional<List<String>> genres);

    @Query("SELECT COUNT(m.id) FROM MovieEntity m WHERE m.user.id = ?1")
    Integer findTotal(Long userId);

    @Query("SELECT SUM(m.duration) FROM MovieEntity m WHERE m.user.id = ?1")
    Integer findTotalDurations(Long userId);

    @Query(value = " SELECT COUNT(mg.movie_entity_id) "
        + " FROM `movie-collection`.movies_genres as mg "
        + " left join `movie-collection`.genres as g on g.id = mg.genres_id "
        + " left join `movie-collection`.movies m on m.id = mg.movie_entity_id "
        + " where g.genre = ?1 and m.user_id = ?2 ",
        nativeQuery = true)
    Integer countMoviesByGenre(String genre , Long userId);
}
