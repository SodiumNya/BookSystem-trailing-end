package com.example.booksystem.mapper;

import com.example.booksystem.core.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface WebMapper {

    @Select("select * from user where username = #{user.username} and password = #{user.password}")
    List<User> findUser(@Param("user") User user);

    @Select("select * from user where #{user.username} = username")
    List<User> getUserById(@Param("user") User user);

    @Insert("insert into user(username, password, role) values(#{user.username}, #{user.password}, #{user.role})")
    Integer insertUser(@Param("user") User user);

    @Select("select role_name from role")
    List<String> getRoles();
}
