package com.example.moviecollectionbackend.repository;

import com.example.moviecollectionbackend.model.entity.PlatformEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatformRepository extends JpaRepository<PlatformEntity, Long> {

    @Query(value = "SELECT * FROM platforms LEFT JOIN movies_platforms mp on platforms.id = mp.platforms_id WHERE mp.movie_entity_id = ?1", nativeQuery = true)
    List<PlatformEntity> findAllByMovieId(Long movieId);

    Optional<PlatformEntity> findByPlatform (String platform);
}
