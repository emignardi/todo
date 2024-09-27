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

    public Optional<Todo> findByTask(String task) {
        return todoRepository.findByTask(task);
    }

    public Todo save(Todo todo) {
        return todoRepository.save(todo);
    }

    public Todo update(int id, Todo todo) {
        Optional<Todo> optionalTodo = todoRepository.findById(id);
        if (optionalTodo.isPresent()) {
            Todo updatedTodo = optionalTodo.get();
            updatedTodo.setTask(todo.getTask());
            updatedTodo.setCategory(todo.getCategory());
            updatedTodo.setDeadline(todo.getDeadline());
            return todoRepository.save(updatedTodo);
        }
        return null;
    }

    public Todo delete(int id) {
        Todo todo = todoRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        todoRepository.delete(todo);
        return todo;
    }

    public Todo delete(String task) {
        Todo todo = todoRepository.findByTask(task).orElseThrow(IllegalArgumentException::new);
        todoRepository.delete(todo);
        return todo;
    }
}
