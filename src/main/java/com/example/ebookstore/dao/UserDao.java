package com.example.ebookstore.dao;

import com.example.ebookstore.entity.User;

import java.util.List;

public interface UserDao {
    User findByUserId(Integer userId);

    User findByUserName(String userName);

    User save(User user);

    List<User> findAll();
}
