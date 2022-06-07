package com.example.moviecollectionbackend.advice;

import com.example.moviecollectionbackend.exception.UserNotFoundException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String , String> handleInvalidArgumentEx(MethodArgumentNotValidException ex){
        Map<String , String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(err -> {
            errors.put("field" , err.getField());
            errors.put("message" , err.getDefaultMessage());
        });

        return errors;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(UserNotFoundException.class)
    public Map<String , String> handleBusinessEx(UserNotFoundException ex){
        Map<String , String> error = new HashMap<>();
        error.put("errorMessage" , ex.getMessage());

        return error;
    }
}
