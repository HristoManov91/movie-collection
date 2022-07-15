package com.example.moviecollectionbackend.repository;

import com.example.moviecollectionbackend.model.entity.UserRoleEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity , Long> {

    Optional<UserRoleEntity> findByRole(String role);
}
