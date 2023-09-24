package com.example.booksystem.service;

import com.example.booksystem.core.entity.User;
import com.example.booksystem.expection.ServiceException;
import com.example.booksystem.mapper.WebMapper;
import com.example.booksystem.uitls.TokenUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebService {

    @Resource
    WebMapper webMapper;

    public User findUser(User user){
        List<User> users = webMapper.findUser(user);

        if(users.isEmpty()){
            throw new ServiceException("500", "用户名或密码错误");
        }
        user = users.get(0);
        String token  = TokenUtils.generateToken(user.getUid(), user.getPassword());
        user.setToken(token);
        return user;
    }

    public Integer insertUser(User user){

        List<User> users = webMapper.getUserById(user);
        if(!users.isEmpty()){
            return 0;
        }
        user.setNickname(user.getUsername());
        Integer res = webMapper.insertUser(user);
        System.out.println("返回结果"+res);
        return res;
    }

    public List<String> getRoles(){
        return webMapper.getRoles();
    }
}
