package com.example.booksystem.mapper;

import com.example.booksystem.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminMapper {

    Integer updateUsersInfo(@Param("user") User user);

    List<User> getUsersInfo(
            @Param("selectedRole") String selectedRole,
            @Param("searchInput") String searchInput,
            @Param("start") Integer start,
            @Param("size") Integer size);

    Integer reSetAvatar(@Param("uid") String uid, @Param("avatarUrl") String avatarUrl);
}
