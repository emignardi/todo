package com.algonquincollege.todo.service;

import com.algonquincollege.todo.model.User;
import com.algonquincollege.todo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepositoryMock;
    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepositoryMock);
        user = new User(
                null,
                "Eric",
                "Mignardi",
                "mignardie",
                "password",
                null);
    }

    @Test
    void shouldFindAllUsers() {
        var users = List.of(new User(
                null,
                "Eric",
                "Mignardi",
                "mignardie",
                "password",
                null));
        when(userRepositoryMock.findAll()).thenReturn(users);
        List<User> savedUsers = userService.findAll();
        assertThat(savedUsers).isEqualTo(users);
        verify(userRepositoryMock, times(1)).findAll();
    }

    @Test
    void shouldFindUserById() {
        when(userRepositoryMock.findById(user.getId())).thenReturn(Optional.of(user));
        User savedUser = userService.findById(user.getId());
        assertThat(savedUser).isEqualTo(user);
        verify(userRepositoryMock, times(1)).findById(user.getId());
    }

    @Test
    void shouldThrowExceptionIfUserNotFound() {
        when(userRepositoryMock.findById(user.getId())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> userService.findById(user.getId())).isInstanceOf(IllegalArgumentException.class);
        verify(userRepositoryMock, times(1)).findById(user.getId());
    }

    @Test
    void shouldSaveUser() {
        userService.save(user);
        verify(userRepositoryMock, times(1)).save(userArgumentCaptor.capture());
        User savedUser = userArgumentCaptor.getValue();
        assertNotNull(savedUser);
        assertThat(savedUser).isEqualTo(user);
    }

    @Test
    void shouldUpdateUser() {
        when(userRepositoryMock.findById(user.getId())).thenReturn(Optional.of(user));
        userService.update(user.getId(), user);
        verify(userRepositoryMock, times(1)).save(userArgumentCaptor.capture());
        User savedUser = userArgumentCaptor.getValue();
        assertNotNull(savedUser);
        assertThat(savedUser).isEqualTo(user);
    }

    @Test
    void shouldDeleteUser() {
        when(userRepositoryMock.findById(user.getId())).thenReturn(Optional.of(user));
        userService.delete(user.getId());
        verify(userRepositoryMock, times(1)).delete(userArgumentCaptor.capture());
        User savedUser = userArgumentCaptor.getValue();
        assertNotNull(savedUser);
        assertThat(savedUser).isEqualTo(user);
    }

    @Test
    void shouldFindByUsername() {
        when(userRepositoryMock.findByUsername(user.getUsername())).thenReturn(Optional.of(user));
        User savedUser = userService.findByUsername(user.getUsername());
        assertThat(savedUser).isEqualTo(user);
        verify(userRepositoryMock, times(1)).findByUsername(user.getUsername());
    }
}