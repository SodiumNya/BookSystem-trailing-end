package com.example.booksystem.mapper;

import com.example.booksystem.core.entity.User;
import com.example.booksystem.core.vo.SplitPageDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

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

    Integer deleteUser(String uid);

    SplitPageDTO getCount(@Param("selectedRole") String selectedRole, @Param("searchInput") String searchInput);
}
