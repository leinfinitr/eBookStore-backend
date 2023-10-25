package com.example.ebookstore.daoimpl;

import com.example.ebookstore.dao.UserDao;
import com.example.ebookstore.entity.User;
import com.example.ebookstore.entity.Userauth;
import com.example.ebookstore.repository.UserRepository;
import com.example.ebookstore.repository.UserauthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserauthRepository userauthRepository;

    @Override
    public User findByUserId(Integer userId) {
        return userRepository.findByUserId(userId);
    }

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByName(userName);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void saveUserAuth(Userauth userauth) {
        userauthRepository.save(userauth);
    }
}
