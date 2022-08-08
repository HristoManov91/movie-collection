package com.example.moviecollectionbackend.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.moviecollectionbackend.model.entity.GenreEntity;
import com.example.moviecollectionbackend.service.impl.AppUserDetailsService;
import com.example.moviecollectionbackend.util.TestDataUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class GenreControllerIT {

    @Autowired
    private MockMvc mockMvc;



    @Autowired
    private TestDataUtils testDataUtils;

    @BeforeEach
    void setUp() {
        GenreEntity action = testDataUtils.createTestGenre("ACTION");
        GenreEntity animation = testDataUtils.createTestGenre("ANIMATION");
        GenreEntity comedy = testDataUtils.createTestGenre("COMEDY");
    }

    @AfterEach
    void tearDown() {
        testDataUtils.cleanUpDatabase();
    }

    @Test
    void testGetAllGenres() throws Exception {
        mockMvc.perform(get("/genres/all"))
            .andExpect(status().isOk());
    }
}