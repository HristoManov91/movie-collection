package com.example.moviecollectionbackend.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.moviecollectionbackend.model.entity.PlatformEntity;
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
class PlatformControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestDataUtils testDataUtils;

    private PlatformEntity hbo, disney, netflix;

    @BeforeEach
    void setUp() {
        hbo = testDataUtils.createTestPlatform("HBOMAX");
        disney = testDataUtils.createTestPlatform("DISNEY+");
        netflix = testDataUtils.createTestPlatform("NETFLIX");
    }

    @AfterEach
    void tearDown() {
        testDataUtils.cleanUpDatabase();
    }

    @Test
    void testFindAllPlatforms() throws Exception {
        mockMvc.perform(get("/platforms/all"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$.length()").value(3))
            .andExpect(jsonPath("$[0]").value(hbo.getPlatform()))
            .andExpect(jsonPath("$[1]").value(disney.getPlatform()))
            .andExpect(jsonPath("$[2]").value(netflix.getPlatform()));
    }
}