package com.example.moviecollectionbackend.service;

import com.example.moviecollectionbackend.model.dto.UserRegisterDTO;
import com.example.moviecollectionbackend.model.entity.UserEntity;

public interface UserService {

    void init();

    boolean registerUser(UserRegisterDTO userRegisterDTO);

    UserEntity findById(Long userId);
}
