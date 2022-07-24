package com.example.moviecollectionbackend.advice;

import com.example.moviecollectionbackend.exception.FullMovieCollectionException;
import com.example.moviecollectionbackend.exception.InvalidIMDbUrlException;
import com.example.moviecollectionbackend.exception.MovieNotFoundException;
import com.example.moviecollectionbackend.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleInvalidArgumentEx(MethodArgumentNotValidException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldErrors().stream().findFirst().get();
        return fieldError.getDefaultMessage();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(MovieNotFoundException.class)
    public String handleMoviesIdEx(MovieNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public String handleUserIdEx(UserNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(FullMovieCollectionException.class)
    public String handleUserIdEx(FullMovieCollectionException ex) {
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidIMDbUrlException.class)
    public String handleIMDbURLError(InvalidIMDbUrlException ex) {
        return ex.getMessage();
    }
}
