package com.algonquincollege.todo.controller;

import com.algonquincollege.todo.model.User;
import com.algonquincollege.todo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users/{id}")
    public User findById(@PathVariable int id) {
        return userService.findById(id).orElseThrow(() -> new RuntimeException("User with id " + id + " not found"));
    }

    @GetMapping("/users")
    public List<User> findAll() {
        return userService.findAll();
    }

    @PostMapping("/users")
    public User save(@RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping("users/{id}")
    public User update(@PathVariable int id, @RequestBody User user) {
        return userService.update(id, user);
    }

    @DeleteMapping("/users/{id}")
    public User delete(@PathVariable int id) {
        return userService.delete(id);
    }
}
