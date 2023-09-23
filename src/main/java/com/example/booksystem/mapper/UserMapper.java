package com.example.booksystem.mapper;

import com.example.booksystem.core.entity.Book;
import com.example.booksystem.core.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    @Options(useGeneratedKeys = true, keyProperty = "uid")
    Integer insertUser(User user);

    List<User> getUserById(String uid);

    List<User> getUserByUsername(String username);

    Integer updateBasicInfo(@Param("user") User user);
    Integer updateCoreInfo(@Param("user") User user);

    Integer addBookToBookShelf(@Param("bookId")String bookId, @Param("uid")String uid);

    Integer removeBookFromBookShelf(@Param("bookId")String bookId, @Param("uid")String uid);
    List<Book> findBookFromBookShelf(@Param("uid") String uid, @Param("start") Integer start, @Param("size") Integer size);

    Integer updateUserAvatar(@Param("avatarUrl") String avatarUrl, @Param("uid") String uid);


}
