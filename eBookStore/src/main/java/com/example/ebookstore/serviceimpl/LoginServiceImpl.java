package com.example.ebookstore.serviceimpl;

import com.example.ebookstore.dao.LoginDao;
import com.example.ebookstore.entity.User;
import com.example.ebookstore.entity.Userauth;
import com.example.ebookstore.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("session")
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginDao loginDao;

    @Override
    public User findByUserName(String userName) {
        return loginDao.findByUserName(userName);
    }

    @Override
    public Userauth findByUserAuthId(Integer userId) {
        return loginDao.findByUserAuthId(userId);
    }
}
