package com.example.booksystem.controller;


import com.example.booksystem.common.RestBean;
import com.example.booksystem.entity.User;
import com.example.booksystem.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/api/add/user")
    @ResponseBody
    public String insertUser(@RequestBody User user) {

        Integer res = userService.insertUser(user);
        if (res >= 0) {
            return RestBean.success("success").asJsonString();
        }

        return RestBean.failure(401, "同名").asJsonString();


    }
}