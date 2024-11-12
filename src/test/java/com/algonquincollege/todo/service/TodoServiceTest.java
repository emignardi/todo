package com.algonquincollege.todo.service;

import com.algonquincollege.todo.model.Todo;
import com.algonquincollege.todo.repository.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class TodoServiceTest {

    @InjectMocks
    private TodoService todoService;
    @Mock
    private TodoRepository todoRepository;
    @Captor
    private ArgumentCaptor<Todo> todoCaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


}