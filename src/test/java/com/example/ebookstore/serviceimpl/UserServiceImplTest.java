package com.example.ebookstore.serviceimpl;

import com.example.ebookstore.dao.BookDao;
import com.example.ebookstore.dao.OrderDao;
import com.example.ebookstore.dao.UserDao;
import com.example.ebookstore.entity.User;
import com.example.ebookstore.entity.Userauth;
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
import static org.mockito.Mockito.*;

class UserServiceImplTest {
    @Mock
    private OrderDao orderDao;

    @Mock
    private UserDao userDao;

    @Mock
    private BookDao bookDao;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findByUserIdReturnsUser() {
        Integer userId = 1;
        User expectedUser = new User();
        when(userDao.findByUserId(userId)).thenReturn(expectedUser);

        User actualUser = userServiceImpl.findByUserId(userId);

        assertEquals(expectedUser, actualUser);
    }

    @Test
    void findByUserNameReturnsUser() {
        String userName = "testUser";
        User expectedUser = new User();
        when(userDao.findByUserName(userName)).thenReturn(expectedUser);

        User actualUser = userServiceImpl.findByUserName(userName);

        assertEquals(expectedUser, actualUser);
    }

    @Test
    void saveByUserSavesUserSuccessfully() {
        User user = new User();
        user.setUserId(1);
        User userToUpdate = new User();
        userToUpdate.setUserId(1);
        when(userDao.findByUserId(user.getUserId())).thenReturn(userToUpdate);
        doNothing().when(userDao).save(userToUpdate);

        userServiceImpl.saveByUser(user);

        verify(userDao).save(userToUpdate);
    }

    @Test
    void findAllReturnsAllUsers() {
        List<User> expectedUsers = new ArrayList<>();
        when(userDao.findAll()).thenReturn(expectedUsers);

        List<User> actualUsers = userServiceImpl.findAll();

        assertEquals(expectedUsers, actualUsers);
    }

    @Test
    void modifyUserStatusModifiesUserStatusSuccessfully() {
        Integer id = 1;
        String status = "Active";
        User user = new User();
        user.setUserId(id);
        when(userDao.findByUserId(id)).thenReturn(user);
        doNothing().when(userDao).save(user);

        userServiceImpl.modifyUserStatus(id, status);

        assertEquals(status, user.getStatus());
    }

    @Test
    void registerRegistersUserSuccessfully() {
        Map<String, String> params = new HashMap<>();
        params.put("username", "testUser");
        params.put("password", "testPassword");
        when(userDao.findByUserName(params.get("username"))).thenReturn(null);

        Map<String, Object> result = userServiceImpl.register(params);

        assertEquals(200, result.get("status"));
    }

    @Test
    void registerReturnsErrorWhenUserExists() {
        Map<String, String> params = new HashMap<>();
        params.put("username", "testUser");
        params.put("password", "testPassword");
        User existingUser = new User();
        when(userDao.findByUserName(params.get("username"))).thenReturn(existingUser);

        Map<String, Object> result = userServiceImpl.register(params);

        assertEquals(400, result.get("status"));
    }

    @Test
    void saveUserauthSavesUserauthSuccessfully() {
        Map<String, Object> userauth = new HashMap<>();
        userauth.put("userid", 1);
        userauth.put("password", "testPassword");
        Userauth userauthToSave = new Userauth();
        userauthToSave.setUserId((Integer) userauth.get("userid"));
        userauthToSave.setPassword((String) userauth.get("password"));

        userServiceImpl.saveUserauth(userauth);

        verify(userDao).saveUserAuth(userauthToSave);
    }
}