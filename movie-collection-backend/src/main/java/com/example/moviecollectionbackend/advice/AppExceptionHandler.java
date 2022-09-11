package com.example.moviecollectionbackend.advice;

import com.example.moviecollectionbackend.exception.FullMovieCollectionException;
import com.example.moviecollectionbackend.exception.InvalidIMDbUrlException;
import com.example.moviecollectionbackend.exception.MovieNotFoundException;
import com.example.moviecollectionbackend.exception.UserNotFoundException;
import com.example.moviecollectionbackend.service.impl.MovieServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {

    private final static Logger LOGGER = LoggerFactory.getLogger(MovieServiceImpl.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleInvalidArgumentEx(MethodArgumentNotValidException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldErrors().stream().findFirst().get();
        LOGGER.error(fieldError.getDefaultMessage());
        return fieldError.getDefaultMessage();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(MovieNotFoundException.class)
    public String handleMoviesIdEx(MovieNotFoundException ex) {
        LOGGER.error(ex.getMessage());
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public String handleUserIdEx(UserNotFoundException ex) {
        LOGGER.error(ex.getMessage());
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(FullMovieCollectionException.class)
    public String handleUserIdEx(FullMovieCollectionException ex) {
        LOGGER.error(ex.getMessage());
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidIMDbUrlException.class)
    public String handleIMDbURLError(InvalidIMDbUrlException ex) {
        LOGGER.error(ex.getMessage());
        return ex.getMessage();
    }
}
