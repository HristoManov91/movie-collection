package com.example.moviecollectionbackend.exception;

public class MovieNotFoundException extends Exception {

    public MovieNotFoundException(String message) {
        super(message);
    }
}
