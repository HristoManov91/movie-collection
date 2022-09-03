package com.example.moviecollectionbackend.model.dto;

import com.example.moviecollectionbackend.model.validation.FieldMatch;
import com.example.moviecollectionbackend.model.validation.UniqueUsername;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@FieldMatch(
    first = "password",
    second = "confirmPassword",
    message = "Passwords do not match."
)

public class UserRegisterDTO {

    @NotEmpty
    @Size(min = 6 , max = 10 , message = "Username length must be between 6 and 10 characters!")
    @UniqueUsername(message = "This username already exists!")
    private String username;

    @NotEmpty
    @Size(min = 6 , max = 10 , message = "Password length must be between 6 and 10 characters!")
    private String password;

    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public UserRegisterDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterDTO setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
