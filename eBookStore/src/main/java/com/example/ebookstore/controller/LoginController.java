package com.example.ebookstore.controller;

import com.example.ebookstore.entity.User;
import com.example.ebookstore.entity.Userauth;
import com.example.ebookstore.service.LoginService;
import com.example.ebookstore.service.TimerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@Scope("session")
@CrossOrigin(originPatterns = "*", allowCredentials = "true")
public class LoginController {
    @Autowired
    LoginService loginService;
    @Autowired
    TimerService timerService;

    @RequestMapping("/login")
    public Map<String, Object> login(@RequestBody Userlogin user) {
        Map<String, Object> result = new HashMap<>();
        User user1 = loginService.findByUserName(user.username);
        if (user1 != null) {
            if (user1.getStatus().equals("禁用")) {
                result.put("status", 400);
                result.put("message", "该用户已被禁用");
                return result;
            }
            Userauth userauth = loginService.findByUserAuthId(user1.getUserId());
            if (Objects.equals(userauth.getPassword(), user.password)) {
                result.put("status", 200);
                result.put("message", "登录成功");
                result.put("user", userauth);
                timerService.startTime(user.username);
                return result;
            }
            result.put("status", 400);
            result.put("message", "密码错误，登录失败");
        } else {
            result.put("status", 400);
            result.put("message", "该用户不存在");
        }
        return result;
    }

    @RequestMapping("/logout")
    public Map<String, Object> logout(@RequestBody Map<String, Object> username) {
        Map<String, Object> result = new HashMap<>();
        // 返回登录的总时长
        result.put("time", timerService.endTime(username.get("username").toString()));
        return result;
    }

    static class Userlogin {
        public String username;
        public String password;
    }
}
