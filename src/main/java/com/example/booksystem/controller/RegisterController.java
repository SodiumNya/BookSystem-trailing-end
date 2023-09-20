//package com.example.booksystem.controller;
//
//
//import com.example.booksystem.common.RestBean;
//import com.example.booksystem.entity.User;
//import com.example.booksystem.service.RegisterService;
//import jakarta.annotation.Resource;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//@Controller
//public class RegisterController {
//    @Resource
//    RegisterService registerService;
//
//    @PostMapping("/api/register")
//    @ResponseBody
//    public String insertUser(@RequestBody User user) {
//
//        Integer res = registerService.insertUser(user);
//        if (res >= 0) {
//            return RestBean.success("success").asJsonString();
//        }
//
//        return RestBean.failure(401, "同名").asJsonString();
//
//
//    }
//}
