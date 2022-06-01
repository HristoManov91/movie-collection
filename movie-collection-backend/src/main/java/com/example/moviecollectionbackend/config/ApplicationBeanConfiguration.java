package com.example.moviecollectionbackend.config;

import com.example.moviecollectionbackend.model.dto.MovieDetailsDto;
import com.example.moviecollectionbackend.model.entity.MovieEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public ModelMapper modelMapper(){
       return new ModelMapper();
    }
}
