package com.algonquincollege.todo.service;

import com.algonquincollege.todo.model.Todo;
import com.algonquincollege.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    public Todo findById(Long id) {
        return todoRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public Todo findByTask(String task) {
        return todoRepository.findByTask(task).orElseThrow(IllegalArgumentException::new);
    }

    public Todo save(Todo todo) {
        return todoRepository.save(todo);
    }

    public Todo update(Long id, Todo todo) {
        Todo updatedTodo = todoRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        updatedTodo.setTask(todo.getTask());
        updatedTodo.setCategory(todo.getCategory());
        updatedTodo.setDeadline(todo.getDeadline());
        todoRepository.save(updatedTodo);
        return updatedTodo;
    }

    public Todo delete(Long id) {
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
