package com.algonquincollege.todo.controller;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Unit tests for HomeController.
 */
@WebMvcTest(HomeController.class)
@AutoConfigureMockMvc(addFilters = false)

public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testHomeEndpoint() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void testLoginEndpoint() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }
    @Test
    public void testCreateTaskEndpoint() throws Exception {
        mockMvc.perform(get("/create-task"))
                .andExpect(status().isOk())
                .andExpect(view().name("create-task"));
    }

    @Test
    public void testRegistrationEndpoint() throws Exception {
        mockMvc.perform(get("/registration"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"));
    }
}
