package com.example.ebookstore.controller;

import com.example.ebookstore.entity.User;
import com.example.ebookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/getUserById")
    public User getUser(@RequestParam(value = "id", defaultValue = "1") String id) {
        return userService.findByUserId(Integer.parseInt(id));
    }

    @RequestMapping("/getUserByUserName")
    public User getUserByUserName(@RequestParam(value = "name") String name) {
        return userService.findByUserName(name);
    }

    @PostMapping("/updateUser")
    public Map<String, Object> updataUser(@RequestBody User user) {
        userService.saveByUser(user);
        Map<String, Object> result = new HashMap<>();
        result.put("status", 200);
        return result;
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @RequestMapping("/modifyUserStatus")
    public Map<String, Object> modifyUserStatus(@RequestParam(value = "id") String id,
                                                @RequestParam(value = "status") String status) {
        userService.modifyUserStatus(Integer.parseInt(id), status);
        Map<String, Object> result = new HashMap<>();
        result.put("status", 200);
        return result;
    }

    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody Map<String, String> params) {
        Map<String, Object> result = userService.register(params);
        if (result.get("status").equals(200)) {
            Map<String, Object> userauth = new HashMap<>();
            userauth.put("userid", result.get("userid"));
            userauth.put("password", params.get("password"));
            userService.saveUserauth(userauth);
        }
        return result;
    }
}
