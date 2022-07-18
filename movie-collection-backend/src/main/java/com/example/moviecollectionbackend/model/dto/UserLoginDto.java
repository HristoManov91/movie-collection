package com.example.moviecollectionbackend.model.dto;

import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

public class UserLoginDto {

    @NotEmpty
    @Length(min = 6 , max = 10)
    private String username;
    @NotEmpty
    @Length(min = 6 , max = 10)
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
