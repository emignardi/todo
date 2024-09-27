package com.algonquincollege.todo.controller;

import com.algonquincollege.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final TodoService todoService;

//    @GetMapping("/")
//    public String home(Model model) {
//        model.addAttribute("todos", todoService.findAll());
//        return "sample";
//    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/create-task")
    public String createTask() {
        return "create-task";
    }
}
