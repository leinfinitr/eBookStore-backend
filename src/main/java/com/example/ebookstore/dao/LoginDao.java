package com.example.ebookstore.dao;

import com.example.ebookstore.entity.User;
import com.example.ebookstore.entity.Userauth;

public interface LoginDao {
    User findByUserName(String userName);

    Userauth findByUserAuthId(Integer userId);
}
