package com.example.moviecollectionbackend.web;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.moviecollectionbackend.model.dto.AddMovieDTO;
import com.example.moviecollectionbackend.model.entity.UserEntity;
import com.example.moviecollectionbackend.service.impl.AppUserDetailsService;
import com.example.moviecollectionbackend.util.TestDataUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class MovieControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestDataUtils testDataUtils;

    private UserEntity testUserEntity;
    private AddMovieDTO testAddMovieDTO;

    @BeforeEach
    void setUp() {
        testDataUtils.initRoles();
        testUserEntity = testDataUtils.createTestUserWithAllRoles("TestUser");
        testAddMovieDTO = testDataUtils.createAddMovieDto();
    }

    @AfterEach
    void tearDown() {
        testDataUtils.cleanUpDatabase();
    }

    @Test
    @WithAnonymousUser
    void testAddMovieByAnonymousUser_Forbidden() throws Exception {

//        AddMovieDTO testMovieDto = new AddMovieDTO()
//            .setTitle1("Title1")
//            .setTitle2("Tile2")
//            .setDuration(120)
//            .setYear(2020)
//            .setDescription("Description")
//            .setTrailerUrl("https://www.youtube.com/watch?v=a8Gx8wiNbs8")
//            .setImdbUrl("https://www.imdb.com/title/tt1630029/")
//            .setBulgarianLanguage(true)
//            .setPosterUrl("https://m.media-amazon.com/images/M/MV5BMWFmYmRiYzMtMTQ4YS00NjA5LTliYTgtMmM3OTc4OGY3MTFkXkEyXkFqcGdeQXVyODk4OTc3MTY@._V1_.jpg")
//            .setGenres(List.of("ACTION"))
//            .setPlatforms(List.of("HBOMAX"));

        mockMvc.perform(post("/movies/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(testAddMovieDTO))
                .with(csrf()))
            .andExpect(status().isForbidden());
    }

//    @Test
//    @WithUserDetails(value = "TestUser")
//    void testAddMovieWithUserAndValidateParams_Created() throws Exception {
//
//        mockMvc.perform(post("/movies/new")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(testAddMovieDTO))
//                .with(csrf()))
//            .andExpect(status().isCreated());
//    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
//    @Test
//    void addMovie() {
//    }
//
//    @Test
//    void findAll() {
//    }
//
//    @Test
//    void getMovieDetailsDto() {
//    }
//
//    @Test
//    void editMovie() {
//    }
//
//    @Test
//    void deleteMovie() {
//    }
//
//    @Test
//    void getStatistics() {
//    }
}