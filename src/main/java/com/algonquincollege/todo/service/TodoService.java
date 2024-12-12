package com.algonquincollege.todo.service;

import com.algonquincollege.todo.model.Todo;
import com.algonquincollege.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The Class TodoService handles the creation/reading/updates/deletion of tasks.
 * <br>
 * This class is the Service layer containing the business logic for a multi-tiered architecture.
 * @author Eric Mignardi
 */

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    /**
     * Finds all available Todos that exist in the database.
     * @return a list Todos
     */
    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    /**
     * Finds a single Todo that exists in the database.
     * @param id
     *              the ID used to locate a stored Todo
     * @return a single Todo
     * @throws IllegalArgumentException if the Todo does not exist
     */
    public Todo findById(Long id) {
        return todoRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    /**
     * Finds a single Todo that exists in the database.
     * @param task
     *              the name of the task used to locate a stored Todo
     * @return a single Todo
     * @throws IllegalArgumentException if the Todo does not exist
     */
    public Todo findByTask(String task) {
        return todoRepository.findByTask(task).orElseThrow(IllegalArgumentException::new);
    }

    /**
     * Saves a Todo into the database using a Todo model object.
     * @param todo
     *              the Todo model object used to create a new Todo
     * @return the new Todo that was saved to the database
     */
    public Todo save(Todo todo) {
        return todoRepository.save(todo);
    }

    /**
     * Locates a Todo from the database by ID and updates that Todo with an updated Todo model object.
     * @param id
     *              the ID used to locate a stored Todo
     * @param todo
     *              the updated Todo model object used to modify a Todo
     * @return the updated Todo that was saved to the database
     * @throws IllegalArgumentException if the Todo does not exist
     */
    public Todo update(Long id, Todo todo) {
        Todo updatedTodo = todoRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        updatedTodo.setTask(todo.getTask());
        updatedTodo.setCategory(todo.getCategory());
        updatedTodo.setDeadline(todo.getDeadline());
        todoRepository.save(updatedTodo);
        return updatedTodo;
    }

    /**
     * Deletes a Todo from the database by ID.
     * @param id
     *              the ID used to locate a stored Todo
     * @return the Todo that was deleted from the database
     * @throws IllegalArgumentException if the Todo does not exist
     */
    public Todo delete(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        todoRepository.delete(todo);
        return todo;
    }

    /**
     * Deletes a Todo from the database by task name.
     * @param task
     *              the task name used to locate a stored Todo
     * @return the Todo that was deleted from the database
     * @throws IllegalArgumentException if the Todo does not exist
     */
    public Todo delete(String task) {
        Todo todo = todoRepository.findByTask(task).orElseThrow(IllegalArgumentException::new);
        todoRepository.delete(todo);
        return todo;
    }
}
