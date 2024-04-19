package com.example.ebookstore.controller;

import com.example.ebookstore.entity.User;
import com.example.ebookstore.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class UserControllerTest {
    @Mock
    UserService userService;

    @InjectMocks
    UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getUserById() {
        String id = "1";
        User expectedUser = new User();
        when(userService.findByUserId(Integer.parseInt(id))).thenReturn(expectedUser);

        User actualUser = userController.getUser(id);

        assertEquals(expectedUser, actualUser);
    }

    @Test
    void getUserByUserName() {
        String name = "testUser";
        User expectedUser = new User();
        when(userService.findByUserName(name)).thenReturn(expectedUser);

        User actualUser = userController.getUserByUserName(name);

        assertEquals(expectedUser, actualUser);
    }

    @Test
    void updateUserSuccessfully() {
        User user = new User();
        doNothing().when(userService).saveByUser(user);

        Map<String, Object> result = userController.updataUser(user);

        assertEquals(200, result.get("status"));
    }

    @Test
    void getAllUsers() {
        List<User> expectedUsers = new ArrayList<>();
        when(userService.findAll()).thenReturn(expectedUsers);

        List<User> actualUsers = userController.getAllUsers();

        assertEquals(expectedUsers, actualUsers);
    }

    @Test
    void modifyUserStatusSuccessfully() {
        String id = "1";
        String status = "Active";
        doNothing().when(userService).modifyUserStatus(Integer.parseInt(id), status);

        Map<String, Object> result = userController.modifyUserStatus(id, status);

        assertEquals(200, result.get("status"));
    }

    @Test
    void registerSuccessfully() {
        Map<String, String> params = new HashMap<>();
        params.put("username", "testUser");
        params.put("password", "testPassword");

        Map<String, Object> expectedResult = new HashMap<>();
        expectedResult.put("status", 200);
        expectedResult.put("userid", 1);

        when(userService.register(params)).thenReturn(expectedResult);
        doNothing().when(userService).saveUserauth(any());

        Map<String, Object> actualResult = userController.register(params);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void registerFails() {
        Map<String, String> params = new HashMap<>();
        params.put("username", "testUser");
        params.put("password", "testPassword");

        Map<String, Object> expectedResult = new HashMap<>();
        expectedResult.put("status", 400);
        expectedResult.put("userid", 1);

        when(userService.register(params)).thenReturn(expectedResult);
        doNothing().when(userService).saveUserauth(any());

        Map<String, Object> actualResult = userController.register(params);

        assertEquals(expectedResult, actualResult);
    }
}