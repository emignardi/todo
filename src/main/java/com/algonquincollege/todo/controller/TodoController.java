package com.algonquincollege.todo.controller;

import com.algonquincollege.todo.model.Todo;
import com.algonquincollege.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/todos")
    public ResponseEntity<List<Todo>> findAll() {
        return ResponseEntity.ok(todoService.findAll());
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity<Todo> findById(@PathVariable Long id) {
        return ResponseEntity.ok(todoService.findById(id));
    }

    @GetMapping("/todos/{task}")
    public ResponseEntity<Todo> findByTask(@PathVariable String task) {
        return ResponseEntity.ok(todoService.findByTask(task));
    }

    @PostMapping("/todos")
    public ResponseEntity<Todo> create (@RequestBody Todo todo) {
        return ResponseEntity.ok(todoService.save(todo));
    }

    @PutMapping("/todos/{id}")
    public ResponseEntity<Todo> update (@PathVariable Long id, @RequestBody Todo todo) {
        return ResponseEntity.ok(todoService.update(id, todo));
    }

    // Ambiguous Mapping
//    @DeleteMapping("/todos/{id}")
//    public ResponseEntity<Todo> delete (@PathVariable int id) {
//        return ResponseEntity.ok(todoService.delete(id));
//    }

    @DeleteMapping("/todos/{task}")
    public ResponseEntity<Todo> delete (@PathVariable String task) {
        return ResponseEntity.ok(todoService.delete(task));
    }
}
