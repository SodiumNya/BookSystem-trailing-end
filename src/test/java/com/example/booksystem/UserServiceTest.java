package com.example.booksystem;

import com.example.booksystem.entity.User;
import com.example.booksystem.mapper.LoginMapper;
import com.example.booksystem.service.RegisterService;
import com.example.booksystem.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private LoginMapper loginMapper;

    @Autowired
    private RegisterService registerService;

    @Autowired
    private UserService userService;

    @Test
    public void testGetUserByUsername() throws SQLIntegrityConstraintViolationException {
        String username = "1234";
        String password = "1";
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        List<User> users = loginMapper.findUser(user);
        users.forEach(System.out::println);
        //        assertEquals(, user.getUsername());
    }

    @Test
    public void add() throws SQLIntegrityConstraintViolationException {
        String username = "1234";
        String password = "1";
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole("学生");
        Integer res = registerService.insertUser(user);
    }

    @Test
    public void update() throws SQLIntegrityConstraintViolationException {
        String username = "1234";
        String password = "1";
        String uid = "2";
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole("学生");
        user.setUid(uid);
        user.setDescribe("hhh");
        Integer res = userService.updateBasicInfo(user);
    }
}

