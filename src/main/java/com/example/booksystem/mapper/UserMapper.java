package com.example.booksystem.mapper;

import com.example.booksystem.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

@Mapper
public interface UserMapper {
    @Options(useGeneratedKeys = true, keyProperty = "uid")
    Integer insertUser(User user);

    List<User> getUserById(String uid);

}
