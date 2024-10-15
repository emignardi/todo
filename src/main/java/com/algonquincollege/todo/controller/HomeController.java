package com.algonquincollege.todo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
