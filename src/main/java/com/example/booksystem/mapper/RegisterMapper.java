package com.example.booksystem.mapper;

import com.example.booksystem.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RegisterMapper {

    @Select("select * from user where #{user.username} = username")
    List<User> getUserById(@Param("user") User user);

    @Insert("insert into user(username, password, role) values(#{user.username}, #{user.password}, #{user.role})")
    Integer insertUser(@Param("user") User user);
}
