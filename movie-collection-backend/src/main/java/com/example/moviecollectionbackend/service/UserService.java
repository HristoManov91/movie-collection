package com.example.moviecollectionbackend.service;

import com.example.moviecollectionbackend.exception.UserNotFoundException;
import com.example.moviecollectionbackend.model.dto.UserRegisterDto;
import com.example.moviecollectionbackend.model.entity.UserEntity;

public interface UserService {

    void init();

    boolean registerUser(UserRegisterDto userRegisterDto);

    UserEntity findById(Long userId) throws UserNotFoundException;
}
