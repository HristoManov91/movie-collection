package com.example.moviecollectionbackend.web;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.moviecollectionbackend.model.dto.UserRegisterDto;
import com.example.moviecollectionbackend.util.TestDataUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestDataUtils testDataUtils;

    @BeforeEach
    void setUp(){
        testDataUtils.initRoles();
    }

    @AfterEach
    void tearDown(){
        testDataUtils.cleanUpDatabase();
    }

    @Test
    void testRegistrationWithoutParams_ReturnBadRequest() throws Exception {
        mockMvc.perform(post("/users/register"))
            .andExpect(status().isBadRequest());
    }

    @Test
    void testRegistrationWithParams_ReturnCreated() throws Exception {
        UserRegisterDto userRegisterDto =
            new UserRegisterDto()
                .setUsername("hrisiq")
                .setPassword("12345678")
                .setConfirmPassword("12345678");

        mockMvc
            .perform(post("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userRegisterDto))
//                .param("username", "hrisiq")
//                .param("password", "12345678")
//                .param("confirmPassword", "12345678")
                .with(csrf()))
            .andExpect(status().isCreated());
    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}