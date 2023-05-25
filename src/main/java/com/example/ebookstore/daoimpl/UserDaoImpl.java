package com.example.ebookstore.daoimpl;

import com.example.ebookstore.dao.UserDao;
import com.example.ebookstore.entity.User;
import com.example.ebookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUserId(Integer userId) {
        return userRepository.findByUserId(userId);
    }

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByName(userName);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
