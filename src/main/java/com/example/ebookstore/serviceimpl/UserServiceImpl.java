package com.example.ebookstore.serviceimpl;

import com.example.ebookstore.dao.UserDao;
import com.example.ebookstore.entity.User;
import com.example.ebookstore.entity.Userauth;
import com.example.ebookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    @Override
    public Map<String, Object> register(Map<String, String> params) {
        String username = params.get("username");
        String password = params.get("password");
        String nickname = params.get("nickname");
        String email = params.get("email");
        String phone = params.get("phone");
        String nation = params.get("nation");
        String province = params.get("province");
        String address = params.get("address");
        User user1 = userDao.findByUserName(username);
        if (user1 != null) {
            return Map.of("status", 400, "message", "用户名已存在");
        } else {
            User user = new User();
            user.setName(username);
            user.setNickname(nickname);
            user.setEmail(email);
            user.setPhone(phone);
            user.setNation(nation);
            user.setProvince(province);
            user.setAddress(address);
            user.setStatus("正常");
            user.setType("用户");
            userDao.save(user);
            return Map.of("status", 200, "message", "注册成功", "userid", user.getUserId());
        }
    }

    @Override
    public void saveUserauth(Map<String, Object> userauth) {
        Userauth userauth1 = new Userauth();
        userauth1.setUserId((Integer) userauth.get("userid"));
        userauth1.setPassword((String) userauth.get("password"));
        userDao.saveUserAuth(userauth1);
    }
}
