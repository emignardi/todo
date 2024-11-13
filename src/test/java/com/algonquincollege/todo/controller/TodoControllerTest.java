package com.algonquincollege.todo.controller;

import com.algonquincollege.todo.model.Todo;
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

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@AutoConfigureMockMvc
class TodoControllerTest {

    @Container
    @ServiceConnection
    static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8.3");
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    private String todoJson;

    @BeforeEach
    void setUp() throws Exception {
        Todo todo = new Todo(
                1L,
                "Task 1",
                "Personal",
                LocalDate.of(2024, 12, 12));
        todoJson = objectMapper.writeValueAsString(todo);
    }

    @Test
    void connectionEstablished() {
        assertThat(mySQLContainer.isCreated()).isTrue();
        assertThat(mySQLContainer.isRunning()).isTrue();
    }

    @Test
    @WithMockUser
    void shouldFindAllTodos() throws Exception {
        mockMvc.perform(post("/todos")
                        .content(todoJson)
                        .contentType("application/json"))
                .andExpect(status().isOk());
        MvcResult mvcResult = mockMvc.perform(get("/todos"))
                .andExpect(status().isOk())
                .andReturn();
        assertThat(mvcResult.getResponse().getContentAsString()).isEqualTo("[" + todoJson + "]");
    }

    @Test
    @WithMockUser
    void shouldFindTodoById() throws Exception {
        mockMvc.perform(post("/todos")
                        .content(todoJson)
                        .contentType("application/json"))
                .andExpect(status().isOk());
        MvcResult mvcResult = mockMvc.perform(get("/todos/{id}", 1L))
                .andExpect(status().isOk())
                .andReturn();
        assertThat(mvcResult.getResponse().getContentAsString()).isEqualTo(todoJson);
    }

    @Test
    @WithMockUser
    void shouldSaveTodo() throws Exception {
        MvcResult postResult = mockMvc.perform(post("/todos")
                        .content(todoJson)
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andReturn();
        assertThat(postResult.getResponse().getContentAsString()).isEqualTo(todoJson);
        MvcResult getResult = mockMvc.perform(get("/todos/{id}", 1L))
                .andExpect(status().isOk())
                .andReturn();
        assertThat(getResult.getResponse().getContentAsString()).isEqualTo(todoJson);
    }

    @Test
    @WithMockUser
    void shouldUpdateTodo() throws Exception {
        mockMvc.perform(post("/todos")
                        .content(todoJson)
                        .contentType("application/json"))
                .andExpect(status().isOk());
        Todo updatedTodo = new Todo(
                1L,
                "Task 1",
                "Work",
                LocalDate.of(2024, 12, 12));
        String updatedTodoJson = objectMapper.writeValueAsString(updatedTodo);
        MvcResult updateResult = mockMvc.perform(put("/todos/{id}", 1L)
                        .content(updatedTodoJson)
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andReturn();
        assertThat(updateResult.getResponse().getContentAsString()).isEqualTo(updatedTodoJson);
        MvcResult getResult = mockMvc.perform(get("/todos/{id}", 1L))
                .andExpect(status().isOk())
                .andReturn();
        assertThat(getResult.getResponse().getContentAsString()).isEqualTo(updatedTodoJson);
    }

    @Test
    @WithMockUser
    void shouldDeleteTodo() throws Exception {
        mockMvc.perform(post("/todos")
                        .content(todoJson)
                        .contentType("application/json"))
                .andExpect(status().isOk());
        MvcResult deleteResult = mockMvc.perform(delete("/todos/{id}", 1L))
                .andExpect(status().isOk())
                .andReturn();
        assertThat(deleteResult.getResponse().getContentAsString()).isEqualTo(todoJson);
    }
}