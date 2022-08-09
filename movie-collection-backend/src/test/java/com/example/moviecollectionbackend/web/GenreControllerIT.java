package com.example.moviecollectionbackend.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.moviecollectionbackend.model.entity.GenreEntity;
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

    private GenreEntity action, animation, comedy;

    @BeforeEach
    void setUp() {
        action = testDataUtils.createTestGenre("ACTION");
        animation = testDataUtils.createTestGenre("ANIMATION");
        comedy = testDataUtils.createTestGenre("COMEDY");
    }

    @AfterEach
    void tearDown() {
        testDataUtils.cleanUpDatabase();
    }

    @Test
    void testGetAllGenres() throws Exception {
        mockMvc.perform(get("/genres/all"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$.length()").value(3))
            .andExpect(jsonPath("$[0]").value(action.getGenre()))
            .andExpect(jsonPath("$[1]").value(animation.getGenre()))
            .andExpect(jsonPath("$[2]").value(comedy.getGenre()));
    }
}