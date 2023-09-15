package com.example.booksystem.mapper;

import com.example.booksystem.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.security.core.parameters.P;

import java.util.List;

@Mapper
public interface UserMapper {
    @Options(useGeneratedKeys = true, keyProperty = "uid")
    Integer insertUser(User user);

    List<User> getUserById(String uid);

    Integer updateBasicInfo(@Param("user") User user);
    Integer updateCoreInfo(@Param("user") User user);

}
