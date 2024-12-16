package com.algonquincollege.todo.controller;

import com.algonquincollege.todo.model.User;
import com.algonquincollege.todo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

/**
 * Unit tests for RegistrationController.
 */
@WebMvcTest(RegistrationController.class)
public class RegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private RegistrationController registrationController;

    @Test
    @WithMockUser
    public void testRegister() throws Exception {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("plainpassword");

        when(passwordEncoder.encode("plainpassword")).thenReturn("encodedpassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        String userJson = "{ \"username\": \"testuser\", \"password\": \"plainpassword\" }";

        mockMvc.perform(post("/register")
                        .with(csrf()) 
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }
}
