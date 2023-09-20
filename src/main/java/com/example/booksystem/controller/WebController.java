package com.example.booksystem.controller;

import cn.hutool.core.util.StrUtil;
import com.example.booksystem.common.RestBean;
import com.example.booksystem.entity.User;
import com.example.booksystem.service.WebService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@ResponseBody
public class WebController {

    @Resource
    WebService webService;

    @PostMapping(value = "/api/login", produces = { "text/html;charset=utf-8" })
    public String login(@RequestBody User user){


        if(StrUtil.isBlank(user.getUsername()) || StrUtil.isBlank(user.getPassword())){
            return RestBean.failure(111, "xxx").asJsonString();
        }
        user = webService.findUser(user);

        return RestBean.success(user).asJsonString();
    }

    @PostMapping("/api/register")
    public String insertUser(@RequestBody User user) {

        Integer res = webService.insertUser(user);
        if (res >= 0) {
            return RestBean.success("success").asJsonString();
        }

        return RestBean.failure(401, "同名").asJsonString();
    }

    @GetMapping("/api/get/role")
    public String getRoles(){
        List<String> roles = webService.getRoles();
        return RestBean.success(roles).asJsonString();
    }


}
