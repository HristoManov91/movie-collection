package com.example.moviecollectionbackend.service;

import com.example.moviecollectionbackend.model.dto.UserRegisterDTO;
import com.example.moviecollectionbackend.model.entity.UserEntity;

public interface UserService {

    void init();

    /**
     * Register User from userRegisterDTO.
     * @param userRegisterDTO This is the user dto object with all data.
     * @return Boolean value.
     */
    boolean registerUser(UserRegisterDTO userRegisterDTO);

    /**
     * Find UserEntity by movie userId.
     * @param userId This is the user id.
     * @return UserEntity.
     */
    UserEntity findById(Long userId);
}
