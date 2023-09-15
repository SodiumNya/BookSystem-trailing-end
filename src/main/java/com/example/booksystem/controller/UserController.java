package com.example.booksystem.controller;


import com.example.booksystem.common.RestBean;
import com.example.booksystem.entity.User;
import com.example.booksystem.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/api/update/basic")
    public String updateBasicInfo(@RequestBody User user){
        Integer res = userService.updateBasicInfo(user);
        if(res >= 0){
            return RestBean.success("success").asJsonString();
        }

        return RestBean.failure(401, "修改失败").asJsonString();
    }

    @PostMapping("/api/update/core")
    public String updateCoreInfo(@RequestBody User user){
        Integer res = userService.updateCoreInfo(user);
        if(res >= 0){
            return RestBean.success("success").asJsonString();
        }

        return RestBean.failure(401, "修改失败").asJsonString();
    }

    @GetMapping("/api/select/{uid}")
    public String reLoadUserinfo(@PathVariable String uid){
        User users = userService.reLoadUserinfo(uid);
        if(users != null){
            return RestBean.success(users).asJsonString();
        }
        return RestBean.failure(401, "获取失败").asJsonString();
    }
}