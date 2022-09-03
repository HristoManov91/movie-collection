package com.example.moviecollectionbackend.web;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.moviecollectionbackend.model.dto.UserRegisterDTO;
import com.example.moviecollectionbackend.model.entity.UserEntity;
import com.example.moviecollectionbackend.util.TestDataUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MethodArgumentNotValidException;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestDataUtils testDataUtils;

    @BeforeEach
    void setUp() {
        testDataUtils.initRoles();
    }

    @AfterEach
    void tearDown() {
        testDataUtils.cleanUpDatabase();
    }

    @Test
    void testRegistrationWithoutParams_ReturnBadRequest() throws Exception {
        mockMvc.perform(post("/users/register"))
            .andExpect(status().isBadRequest());
    }

    @Test
    void testRegistrationWithInvalidUsernameLength_ReturnExceptionWithMessage() throws Exception {
        UserRegisterDTO userRegisterDTO =
            new UserRegisterDTO()
                .setUsername("hrisiqmanova")
                .setPassword("12345678")
                .setConfirmPassword("12345678");

        mockMvc
            .perform(post("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userRegisterDTO))
                .with(csrf()))
            .andExpect(status().isBadRequest())
            .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException))
            .andExpect(content().string("Username length must be between 6 and 10 characters!"));
    }

    @Test
    void testRegistrationWithInvalidPasswordLength_ReturnExceptionWithMessage() throws Exception {

        UserRegisterDTO userRegisterDTO =
            new UserRegisterDTO()
                .setUsername("hrisiq")
                .setPassword("12345678910")
                .setConfirmPassword("12345678910");

        mockMvc
            .perform(post("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userRegisterDTO))
                .with(csrf()))
            .andExpect(status().isBadRequest())
            .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException))
            .andExpect(content().string("Password length must be between 6 and 10 characters!"));
    }

    @Test
    void testRegistrationWithUsernameExist_ReturnExceptionWithMessage() throws Exception {
        UserEntity hristo = testDataUtils.createTestUserWithAllRoles("hristo");

        UserRegisterDTO userRegisterDTO =
            new UserRegisterDTO()
                .setUsername("hristo")
                .setPassword("12345678")
                .setConfirmPassword("12345678");

        mockMvc
            .perform(post("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userRegisterDTO))
                .with(csrf()))
            .andExpect(status().isBadRequest())
            .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException))
            .andExpect(content().string("This username already exists!"));
    }

    @Test
    void testRegistrationWithDifferentPassword_ReturnExceptionWithMessage() throws Exception {

        UserRegisterDTO userRegisterDTO =
            new UserRegisterDTO()
                .setUsername("hristooo")
                .setPassword("123456789")
                .setConfirmPassword("12345678");

        mockMvc
            .perform(post("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userRegisterDTO))
                .with(csrf()))
            .andExpect(status().isBadRequest())
            .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException))
            .andExpect(content().string("Passwords do not match."));
    }

    @Test
    void testRegistrationWithParams_ReturnCreated() throws Exception {
        UserRegisterDTO userRegisterDTO =
            new UserRegisterDTO()
                .setUsername("hrisiq")
                .setPassword("12345678")
                .setConfirmPassword("12345678");

        mockMvc
            .perform(post("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userRegisterDTO))
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