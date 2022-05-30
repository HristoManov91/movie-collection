package com.example.moviecollectionbackend.repository;

import com.example.moviecollectionbackend.model.entity.PlatformEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatformRepository extends JpaRepository<PlatformEntity, Long> {

}
