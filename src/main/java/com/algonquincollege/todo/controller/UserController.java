package com.algonquincollege.todo.controller;

import com.algonquincollege.todo.model.User;
import com.algonquincollege.todo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users/{id}")
    public ResponseEntity<Optional<User>> findById(@PathVariable int id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping("/users")
    public ResponseEntity<User> save(@RequestBody User user) {
        return ResponseEntity.ok(userService.save(user));
    }

    @PutMapping("users/{id}")
    public ResponseEntity<User> update(@PathVariable int id, @RequestBody User user) {
        return ResponseEntity.ok(userService.update(id, user));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> delete(@PathVariable int id) {
        return ResponseEntity.ok(userService.delete(id));
    }
}
