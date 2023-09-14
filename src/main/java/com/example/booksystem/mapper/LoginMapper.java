package com.example.booksystem.mapper;

import com.example.booksystem.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LoginMapper {

    @Select("select * from user where username = #{user.username} and password = #{user.password}")
    List<User> findUser(@Param("user") User user);
}
