package com.example.moviecollectionbackend.web;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.moviecollectionbackend.model.dto.MovieDTO;
import com.example.moviecollectionbackend.model.dto.SearchParamsDTO;
import com.example.moviecollectionbackend.model.entity.UserEntity;
import com.example.moviecollectionbackend.util.TestDataUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class MovieControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestDataUtils testDataUtils;

    private UserEntity testUserEntity;
    private MovieDTO testMovieDTO;

    @BeforeEach
    void setUp() {
        testDataUtils.initRoles();
        testUserEntity = testDataUtils.createTestUserWithAllRoles("TestUser");
        testMovieDTO = testDataUtils.createMovieDto();
    }

    @AfterEach
    void tearDown() {
        testDataUtils.cleanUpDatabase();
    }

    // Path: /movies/new
    @Test
    @WithAnonymousUser
    void testAddMovieByAnonymousUser_ReturnForbidden() throws Exception {

        mockMvc.perform(post("/movies/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(testMovieDTO))
                .with(csrf()))
            .andExpect(status().isForbidden());
    }

    @Test
    void testAddMovieWithUserAndValidateParams_Created() throws Exception {

        mockMvc.perform(post("/movies/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(testMovieDTO))
                .with(csrf()))
            .andExpect(status().isCreated());
    }

    // Path: /movies/all

    @Test
    @WithAnonymousUser
    void testFindAllMoviesByAnonymousUser_ReturnForbidden() throws Exception {
        SearchParamsDTO testSearchParamsDTO = new SearchParamsDTO();

        mockMvc
            .perform(get("/movies/all")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(testSearchParamsDTO))
                .with(csrf()))
            .andExpect(status().isForbidden());
    }

    // Path: /movies/{movieId}

    @Test
    @WithAnonymousUser
    void testGetMovieDetailsDtoByAnonymousUser_ReturnForbidden() throws Exception {
        Long movieId = 1L;

        mockMvc
            .perform(get("/movies/{movieId}", movieId)
                .contentType(MediaType.APPLICATION_JSON)
                .with(csrf()))
            .andExpect(status().isForbidden());
    }

    // Path: /movie/edit

    @Test
    @WithAnonymousUser
    void testEditMovieByAnonymousUser_ReturnForbidden() throws Exception {
        MovieDTO testMovieDTO = new MovieDTO();

        mockMvc
            .perform(post("/movies/edit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(testMovieDTO))
                .with(csrf()))
            .andExpect(status().isForbidden());
    }

    // Path: /movies/delete

    @Test
    @WithAnonymousUser
    void testDeleteMovieByAnonymousUser_ReturnForbidden() throws Exception {
        Long movieId = 2L;

        mockMvc
            .perform(delete("/movies/delete")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(movieId))
                .with(csrf()))
            .andExpect(status().isForbidden());
    }

    // Path: /movies/statistics

    @Test
    @WithAnonymousUser
    void testGetStatisticsByAnonymousUser_ReturnForbidden() throws Exception {

        mockMvc
            .perform(get("/movies/statistics")
                .with(csrf()))
            .andExpect(status().isForbidden());
    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}