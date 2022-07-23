package com.example.moviecollectionbackend.exception;

public class InvalidIMDbUrlException extends Exception {

    public InvalidIMDbUrlException (String message) {
        super(message);
    }
}
