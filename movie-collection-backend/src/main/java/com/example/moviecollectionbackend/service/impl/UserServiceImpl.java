package com.example.moviecollectionbackend.service.impl;

import com.example.moviecollectionbackend.repository.UserRepository;
import com.example.moviecollectionbackend.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
