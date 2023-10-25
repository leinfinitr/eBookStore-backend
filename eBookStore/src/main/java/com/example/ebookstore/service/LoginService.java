package com.example.ebookstore.service;

import com.example.ebookstore.entity.User;
import com.example.ebookstore.entity.Userauth;

public interface LoginService {
    User findByUserName(String userName);

    Userauth findByUserAuthId(Integer userId);
}
