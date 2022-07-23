package com.example.moviecollectionbackend.model.dto;

import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class UserLoginDto {

    @NotBlank(message = "Username is required and cannot be empty string!")
    @Length(min = 6, max = 10, message = "Username length must be between 6 and 10 characters!")
    private String username;
    @NotBlank(message = "Password is required and cannot be empty string!")
    @Length(min = 6, max = 10, message = "Password length must be between 6 and 10 characters!")
    private String password;

    public UserLoginDto() {
    }

    public String getUsername() {
        return username;
    }

    public UserLoginDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserLoginDto setPassword(String password) {
        this.password = password;
        return this;
    }
}
