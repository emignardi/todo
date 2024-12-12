package com.algonquincollege.todo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * The Class HomeController handles the forwarding of application users to specific pages based on the URL path.
 * <br>
 * The class contains routes for navigating to the index (home), login, task creation, and registration pages.
 */

@Controller
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/create-task")
    public String createTask() {
        return "create-task";
    }

    @GetMapping("/registration")
    public String registration() {
        return "register";
    }
}
