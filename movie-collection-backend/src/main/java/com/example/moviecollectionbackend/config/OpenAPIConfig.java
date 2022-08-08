package com.example.moviecollectionbackend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI OpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Movie collection API")
                .version("1.0.0")
                .contact(new Contact()
                    .name("Hristo Manov")
                    .email("manoraev@gmail.com"))
                .description("My movie-collection API"));
    }
}
