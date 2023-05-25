package com.example.ebookstore.serviceimpl;

import com.example.ebookstore.dao.UserDao;
import com.example.ebookstore.entity.User;
import com.example.ebookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User findByUserId(Integer userId) {
        return userDao.findByUserId(userId);
    }

    @Override
    public User findByUserName(String userName) {
        return userDao.findByUserName(userName);
    }

    @Override
    public void saveByUser(User user) {
        User user1 = userDao.findByUserId(user.getUserId());
        user1.setNickname(user.getNickname());
        user1.setPhone(user.getPhone());
        user1.setEmail(user.getEmail());
        user1.setNation(user.getNation());
        user1.setProvince(user.getProvince());
        user1.setAddress(user.getAddress());
        userDao.save(user1);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public void modifyUserStatus(Integer id, String status) {
        User user = userDao.findByUserId(id);
        user.setStatus(status);
        userDao.save(user);
    }
}
