package com.example.booksystem.service;

import com.example.booksystem.entity.User;
import com.example.booksystem.expection.ServiceException;
import com.example.booksystem.mapper.UserMapper;
import com.example.booksystem.uitls.TokenUtils;
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
            throw new ServiceException("500", "未知错误");
        }

        return list.get(0);

    }

    public Integer updateBasicInfo(User user){
        List<User> list = userMapper.getUserById(user.getUid());
        if(list.isEmpty()){
            return -1;
        }
        return userMapper.updateBasicInfo(user);
    }

    public Integer updateCoreInfo(User user){
        List<User> list = userMapper.getUserById(user.getUid());
        if(list.isEmpty()){
            return -1;
        }
        return userMapper.updateCoreInfo(user);
    }

    public User reLoadUserinfo(String uid){
        List<User> list = userMapper.getUserById(uid);
        if(list.isEmpty()){
            throw new ServiceException("500", "未知错误");
        }
        User user = list.get(0);
        String token = TokenUtils.generateToken(user.getUid(), user.getPassword());
        user.setToken(token);
        return user;

    }
}
