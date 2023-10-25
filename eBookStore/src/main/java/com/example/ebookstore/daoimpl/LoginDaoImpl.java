package com.example.ebookstore.daoimpl;

import com.example.ebookstore.dao.LoginDao;
import com.example.ebookstore.entity.User;
import com.example.ebookstore.entity.Userauth;
import com.example.ebookstore.repository.UserRepository;
import com.example.ebookstore.repository.UserauthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDaoImpl implements LoginDao {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserauthRepository userauthRepository;

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByName(userName);
    }

    @Override
    public Userauth findByUserAuthId(Integer userId) {
        return userauthRepository.findByUserId(userId);
    }
}
