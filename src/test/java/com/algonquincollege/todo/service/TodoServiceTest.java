package com.algonquincollege.todo.service;

import com.algonquincollege.todo.model.Todo;
import com.algonquincollege.todo.repository.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TodoServiceTest {

    @InjectMocks
    private TodoService todoService;
    @Mock
    private TodoRepository todoRepositoryMock;
    @Captor
    private ArgumentCaptor<Todo> todoArgumentCaptor;
    private Todo todo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        todoService = new TodoService(todoRepositoryMock);
        todo = new Todo(
                null,
                "Task 1",
                "Personal",
                LocalDate.of(2024,12,12));
    }

    @Test
    void shouldFindAllTodos() {
        var todos = List.of(new Todo(
                null,
                "Task 1",
                "Personal",
                LocalDate.of(2024,12,12)));
        when(todoRepositoryMock.findAll()).thenReturn(todos);
        List<Todo> savedTodos = todoService.findAll();
        assertThat(savedTodos).isEqualTo(todos);
        verify(todoRepositoryMock, times(1)).findAll();
    }

    @Test
    void shouldFindTodoById() {
        when(todoRepositoryMock.findById(todo.getId())).thenReturn(Optional.of(todo));
        Todo savedTodo = todoService.findById(todo.getId());
        assertThat(todo).isEqualTo(savedTodo);
        verify(todoRepositoryMock, times(1)).findById(todo.getId());
    }

    @Test
    void shouldThrowExceptionIfTodoNotFound() {
        when(todoRepositoryMock.findById(todo.getId())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> todoService.findById(todo.getId())).isInstanceOf(IllegalArgumentException.class);
        verify(todoRepositoryMock, times(1)).findById(todo.getId());
    }

    @Test
    void shouldFindTodoByTask() {
        when(todoRepositoryMock.findByTask(todo.getTask())).thenReturn(Optional.of(todo));
        Todo savedTodo = todoService.findByTask(todo.getTask());
        assertThat(todo).isEqualTo(savedTodo);
        verify(todoRepositoryMock, times(1)).findByTask(todo.getTask());
    }

    @Test
    void shouldSaveTodo() {
        todoService.save(todo);
        verify(todoRepositoryMock, times(1)).save(todoArgumentCaptor.capture());
        Todo savedTodo = todoArgumentCaptor.getValue();
        assertNotNull(savedTodo);
        assertThat(savedTodo).isEqualTo(todo);
    }

    @Test
    void shouldUpdateTodo() {
        when(todoRepositoryMock.findById(todo.getId())).thenReturn(Optional.of(todo));
        todoService.update(todo.getId(), todo);
        verify(todoRepositoryMock, times(1)).save(todoArgumentCaptor.capture());
        Todo savedTodo = todoArgumentCaptor.getValue();
        assertNotNull(savedTodo);
        assertThat(savedTodo).isEqualTo(todo);
    }

    @Test
    void shouldDeleteTodoById() {
        when(todoRepositoryMock.findById(todo.getId())).thenReturn(Optional.of(todo));
        todoService.delete(todo.getId());
        verify(todoRepositoryMock, times(1)).delete(todoArgumentCaptor.capture());
        Todo savedTodo = todoArgumentCaptor.getValue();
        assertNotNull(savedTodo);
        assertThat(savedTodo).isEqualTo(todo);
    }

    @Test
    void shouldDeleteTodoByTask() {
        when(todoRepositoryMock.findByTask(todo.getTask())).thenReturn(Optional.of(todo));
        todoService.delete(todo.getTask());
        verify(todoRepositoryMock, times(1)).delete(todoArgumentCaptor.capture());
        Todo savedTodo = todoArgumentCaptor.getValue();
        assertNotNull(savedTodo);
        assertThat(savedTodo).isEqualTo(todo);
    }
}