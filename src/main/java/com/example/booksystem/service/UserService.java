package com.example.booksystem.service;

import com.example.booksystem.entity.User;
import com.example.booksystem.expection.ServiceException;
import com.example.booksystem.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Resource
    UserMapper userMapper;

    public Integer insertUser(User user){

       Integer res = userMapper.insertUser(user);
       System.out.println("返回结果"+res);
       return res;
    }

    public User getUserById(String uid){
        List<User> list = userMapper.getUserById(uid);
        if(list.isEmpty()){
            throw new ServiceException("未知错误");
        }

        return list.get(0);

    }
}
