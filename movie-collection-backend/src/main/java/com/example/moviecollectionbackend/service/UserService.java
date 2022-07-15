package com.example.moviecollectionbackend.service;

import com.example.moviecollectionbackend.model.dto.UserRegisterDto;

public interface UserService {

    void init();

    boolean registerUser(UserRegisterDto userRegisterDto);

}
