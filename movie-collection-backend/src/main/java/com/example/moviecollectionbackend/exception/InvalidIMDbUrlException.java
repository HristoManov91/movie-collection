package com.example.moviecollectionbackend.exception;

public class InvalidIMDbUrlException extends RuntimeException {

    public InvalidIMDbUrlException (String message) {
        super(message);
    }
}
