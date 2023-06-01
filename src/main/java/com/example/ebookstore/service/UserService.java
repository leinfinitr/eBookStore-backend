package com.example.ebookstore.service;

import com.example.ebookstore.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    User findByUserId(Integer userId);

    User findByUserName(String userName);

    void saveByUser(User user);

    List<User> findAll();

    void modifyUserStatus(Integer id, String status);

    Map<String, Object> register(Map<String, String> params);

    void saveUserauth(Map<String, Object> userauth);
}
