package com.algonquincollege.todo.service;

import com.algonquincollege.todo.model.Todo;
import com.algonquincollege.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    public Optional<Todo> findById(int id) {
        return todoRepository.findById(id);
    }

    public Todo save(Todo todo) {
        return todoRepository.save(todo);
    }

    public Todo update(int id, Todo todo) {
        Optional<Todo> optionalTodo = todoRepository.findById(id);
        if (optionalTodo.isPresent()) {
            Todo updatedTodo = optionalTodo.get();
            updatedTodo.setTitle(todo.getTitle());
            updatedTodo.setDeadline(todo.getDeadline());
            return todoRepository.save(updatedTodo);
        }
        return null;
    }

    public Todo delete(int id) {
        Optional<Todo> optionalTodo = todoRepository.findById(id);
        if (optionalTodo.isPresent()) {
            Todo deletedTodo = optionalTodo.get();
            todoRepository.delete(deletedTodo);
        }
        return null;
    }
}
