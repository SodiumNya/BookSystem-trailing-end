//package com.example.booksystem.controller;
//
//import cn.hutool.core.util.StrUtil;
//import com.example.booksystem.common.RestBean;
//import com.example.booksystem.entity.User;
//import com.example.booksystem.service.WebService;
//import jakarta.annotation.Resource;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class LoginController {
//
//    @Resource
//    WebService webService;
//
//    @PostMapping(value = "/api/login", produces = { "text/html;charset=utf-8" })
//    public String login(@RequestBody User user){
//
//
//        if(StrUtil.isBlank(user.getUsername()) || StrUtil.isBlank(user.getPassword())){
//            return RestBean.failure(111, "xxx").asJsonString();
//        }
//        user = webService.findUser(user);
//
//        return RestBean.success(user).asJsonString();
//    }
//}
