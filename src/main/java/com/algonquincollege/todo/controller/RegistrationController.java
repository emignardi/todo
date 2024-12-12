package com.algonquincollege.todo.controller;

import com.algonquincollege.todo.model.User;
import com.algonquincollege.todo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * The Class RegistrationController handles incoming HTTP Post requests to register a new user into the system.
 * @author Eric Mignardi
 */

@RestController
@RequiredArgsConstructor
public class RegistrationController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Registers a new user into the system using a User model object, received in the body of an HTTP Post request.
     * <br>
     * For security purposes, the password received in the request is encoded using a BCrypt password encoder.
     * @see com.algonquincollege.todo.configuration.SecurityConfig
     * @param user
     *              the User model object used to register a new user
     * @return the ResponseEntity with the 200 OK status and a body containing a key-value pair with a success message
     */
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody User user) {
        HashMap<String, Object> response = new HashMap<>();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        response.put("success", true);
//        response.put("User", savedUser);
        return ResponseEntity.ok(response);
    }
}
