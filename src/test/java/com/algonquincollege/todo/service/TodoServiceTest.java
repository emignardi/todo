package com.algonquincollege.todo.service;

import com.algonquincollege.todo.model.Todo;
import com.algonquincollege.todo.repository.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;

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