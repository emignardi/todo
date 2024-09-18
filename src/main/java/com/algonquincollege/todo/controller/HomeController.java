package com.algonquincollege.todo.controller;

import com.algonquincollege.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final TodoService todoService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("todos", todoService.findAll());
        return "index";
    }

    @GetMapping("/create-todo")
    public String createTodo() {
        return "create-todo";
    }
}
