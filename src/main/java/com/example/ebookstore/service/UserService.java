package com.example.ebookstore.service;

import com.example.ebookstore.entity.User;

import java.util.List;

public interface UserService {
    User findByUserId(Integer userId);

    User findByUserName(String userName);

    void saveByUser(User user);

    List<User> findAll();

    void modifyUserStatus(Integer id, String status);
}
