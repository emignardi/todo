package com.algonquincollege.todo.controller;

import com.algonquincollege.todo.model.Todo;
import com.algonquincollege.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/todos")
    public ResponseEntity<List<Todo>> findAll() {
        return ResponseEntity.ok(todoService.findAll());
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity<Optional<Todo>> findById(@PathVariable int id) {
        return ResponseEntity.ok(todoService.findById(id));
    }

    @PostMapping("/todos")
    public ResponseEntity<Todo> create (@RequestBody Todo todo) {
        return ResponseEntity.ok(todoService.save(todo));
    }

    @PutMapping("/todos/{id}")
    public ResponseEntity<Todo> update (@PathVariable int id, @RequestBody Todo todo) {
        return ResponseEntity.ok(todoService.update(id, todo));
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity<Todo> delete (@PathVariable int id) {
        return ResponseEntity.ok(todoService.delete(id));
    }
}
