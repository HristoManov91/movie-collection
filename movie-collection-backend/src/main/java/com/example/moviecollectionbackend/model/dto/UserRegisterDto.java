package com.example.moviecollectionbackend.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserRegisterDto {

    @NotEmpty
    @Size(min = 6 , max = 10)
    private String username;
    @NotEmpty
    @Size(min = 6 , max = 10)
    private String password;
    @NotEmpty
    @Size(min = 6 , max = 10)
    //ToDo custom valid equals password
    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public UserRegisterDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterDto setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
