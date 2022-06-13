package com.example.moviecollectionbackend.advice;

import com.example.moviecollectionbackend.exception.UserNotFoundException;
import java.net.URISyntaxException;
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
        //ToDo fix message
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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(URISyntaxException.class)
    public String handleIMDbURLError(URISyntaxException ex){
        return ex.getMessage();
//        return "IMDb URL is not valid!";
    }
}
