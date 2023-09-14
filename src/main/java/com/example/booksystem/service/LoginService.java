package com.example.booksystem.service;

import com.example.booksystem.entity.User;
import com.example.booksystem.expection.ServiceException;
import com.example.booksystem.mapper.LoginMapper;
import com.example.booksystem.uitls.TokenUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {

    @Resource
    LoginMapper loginMapper;

    public User findUser(User user){
        List<User> users = loginMapper.findUser(user);

        if(users.isEmpty()){
            throw new ServiceException("用户名或密码错误");
        }
        user = users.get(0);
        String token  = TokenUtils.generateToken(user.getUid(), user.getPassword());
        user.setToken(token);
        return user;
    }
}
