package com.example.moviecollectionbackend.advice;

import com.example.moviecollectionbackend.exception.MovieNotFoundException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
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
    public String handleInvalidArgumentEx(MethodArgumentNotValidException ex){
        //ToDo fix message
        FieldError fieldError = ex.getBindingResult().getFieldErrors().stream().findFirst().get();
        return fieldError.getDefaultMessage();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(MovieNotFoundException.class)
    public String handleBusinessEx(MovieNotFoundException ex){
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(URISyntaxException.class)
    public String handleIMDbURLError(URISyntaxException ex){
        return ex.getMessage();
        //ToDo return "IMDb URL is not valid!";
    }
}
