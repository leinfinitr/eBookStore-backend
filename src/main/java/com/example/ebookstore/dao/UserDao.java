package com.example.ebookstore.dao;

import com.example.ebookstore.entity.User;
import com.example.ebookstore.entity.Userauth;

import java.util.List;

public interface UserDao {
    User findByUserId(Integer userId);

    User findByUserName(String userName);

    void save(User user);

    List<User> findAll();

    void saveUserAuth(Userauth userauth);
}
