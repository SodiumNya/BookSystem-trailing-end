package com.example.booksystem.service;

import com.example.booksystem.entity.User;
import com.example.booksystem.mapper.RegisterMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterService {

    @Resource
    RegisterMapper registerMapper;

    public Integer insertUser(User user){

        List<User> users = registerMapper.getUserById(user);
        if(!users.isEmpty()){
            return 0;
        }

        Integer res = registerMapper.insertUser(user);
        System.out.println("返回结果"+res);
        return res;
    }
}
