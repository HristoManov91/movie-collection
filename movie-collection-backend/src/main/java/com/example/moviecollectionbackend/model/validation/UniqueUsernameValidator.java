package com.example.moviecollectionbackend.model.validation;

import com.example.moviecollectionbackend.repository.UserRepository;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername , String> {

    private final UserRepository userRepo;

    public UniqueUsernameValidator(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return userRepo.findByUsername(value).isEmpty();
    }
}
