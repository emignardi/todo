package com.algonquincollege.todo.controller;

import com.algonquincollege.todo.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@AutoConfigureMockMvc
class UserControllerTest {

    @Container
    @ServiceConnection
    static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8.3");
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    private String userJson;

    @BeforeEach
    void setUp() throws Exception {
        User user = new User(
                1L,
                "Eric",
                "Mignardi",
                "mignardie",
                "password",
                "USER");
        userJson = objectMapper.writeValueAsString(user);
    }

    @Test
    void connectionEstablished() {
        assertThat(mySQLContainer.isCreated()).isTrue();
        assertThat(mySQLContainer.isRunning()).isTrue();
    }

    @Test
    @WithMockUser
    void shouldFindAllUsers() throws Exception {
        mockMvc.perform(post("/users")
                        .contentType("application/json")
                        .content(userJson))
                .andExpect(status().isOk());
        MvcResult getResult = mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andReturn();
        assertThat(getResult.getResponse().getContentAsString()).isEqualTo("[" + userJson + "]");
    }

    @Test
    @WithMockUser
    void shouldFindUserById() throws Exception {
        mockMvc.perform(post("/users")
                        .contentType("application/json")
                        .content(userJson))
                .andExpect(status().isOk());
        MvcResult getResult = mockMvc.perform(get("/users/{id}", 1L))
                .andExpect(status().isOk())
                .andReturn();
        assertThat(getResult.getResponse().getContentAsString()).isEqualTo(userJson);
    }

    @Test
    @WithMockUser
    void shouldSaveUser() throws Exception {
        mockMvc.perform(post("/users")
                        .contentType("application/json")
                        .content(userJson))
                .andExpect(status().isOk());
        MvcResult getResult = mockMvc.perform(get("/users/{id}", 1L))
                .andExpect(status().isOk())
                .andReturn();
        assertThat(getResult.getResponse().getContentAsString()).isEqualTo(userJson);
    }

    @Test
    @WithMockUser
    void shouldUpdateUser() throws Exception {
        mockMvc.perform(post("/users")
                        .contentType("application/json")
                        .content(userJson))
                .andExpect(status().isOk());
        User updatedUser = new User(
                1L,
                "Michael",
                "Jasper",
                "mignardie",
                "password",
                "USER");
        String updatedUserJson = objectMapper.writeValueAsString(updatedUser);
        MvcResult putResult = mockMvc.perform(put("/users/{id}", 1L)
                        .contentType("application/json")
                        .content(updatedUserJson))
                .andExpect(status().isOk())
                .andReturn();
        assertThat(putResult.getResponse().getContentAsString()).isEqualTo(updatedUserJson);
        MvcResult getResult = mockMvc.perform(get("/users/{id}", 1L))
                .andExpect(status().isOk())
                .andReturn();
        assertThat(getResult.getResponse().getContentAsString()).isEqualTo(updatedUserJson);
    }

    @Test
    @WithMockUser
    void shouldDeleteUser() throws Exception {
        mockMvc.perform(post("/users")
                        .contentType("application/json")
                        .content(userJson))
                .andExpect(status().isOk());
        MvcResult deleteResult = mockMvc.perform(delete("/users/{id}", 1L))
                .andExpect(status().isOk())
                .andReturn();
        assertThat(deleteResult.getResponse().getContentAsString()).isEqualTo(userJson);
    }
}