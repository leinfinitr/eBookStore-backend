package com.example.ebookstore.controller;

import com.example.ebookstore.entity.User;
import com.example.ebookstore.entity.Userauth;
import com.example.ebookstore.repository.UserRepository;
import com.example.ebookstore.repository.UserauthRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class LoginControllerTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private UserauthRepository userauthRepository;

    @InjectMocks
    private LoginController loginController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loginWithValidCredentialsReturnsSuccess() {
        LoginController.Userlogin userlogin = new LoginController.Userlogin();
        userlogin.username = "testUser";
        userlogin.password = "testPassword";

        User user = new User();
        user.setStatus("Active");
        user.setUserId(1);

        Userauth userauth = new Userauth();
        userauth.setPassword("testPassword");

        when(userRepository.findByName(userlogin.username)).thenReturn(user);
        when(userauthRepository.findByUserId(user.getUserId())).thenReturn(userauth);

        Map<String, Object> result = loginController.login(userlogin);

        assertEquals(200, result.get("status"));
        assertEquals("登录成功", result.get("message"));
        assertEquals(userauth, result.get("user"));
    }

    @Test
    void loginWithInvalidCredentialsReturnsFailure() {
        LoginController.Userlogin userlogin = new LoginController.Userlogin();
        userlogin.username = "testUser";
        userlogin.password = "wrongPassword";

        User user = new User();
        user.setStatus("Active");
        user.setUserId(1);

        Userauth userauth = new Userauth();
        userauth.setPassword("testPassword");

        when(userRepository.findByName(userlogin.username)).thenReturn(user);
        when(userauthRepository.findByUserId(user.getUserId())).thenReturn(userauth);

        Map<String, Object> result = loginController.login(userlogin);

        assertEquals(400, result.get("status"));
        assertEquals("密码错误，登录失败", result.get("message"));
    }

    @Test
    void loginWithNonexistentUserReturnsFailure() {
        LoginController.Userlogin userlogin = new LoginController.Userlogin();
        userlogin.username = "nonexistentUser";
        userlogin.password = "testPassword";

        when(userRepository.findByName(userlogin.username)).thenReturn(null);

        Map<String, Object> result = loginController.login(userlogin);

        assertEquals(400, result.get("status"));
        assertEquals("该用户不存在", result.get("message"));
    }

    @Test
    void loginWithDisabledUserReturnsFailure() {
        LoginController.Userlogin userlogin = new LoginController.Userlogin();
        userlogin.username = "disabledUser";
        userlogin.password = "testPassword";

        User user = new User();
        user.setStatus("禁用");
        user.setUserId(1);

        when(userRepository.findByName(userlogin.username)).thenReturn(user);

        Map<String, Object> result = loginController.login(userlogin);

        assertEquals(400, result.get("status"));
        assertEquals("该用户已被禁用", result.get("message"));
    }
}