package com.example.booksystem.mapper;

import com.example.booksystem.entity.Book;
import com.example.booksystem.entity.BookWithShelfStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookMapper {

    List<Book> getBookList(String data, Integer start, Integer size);
    List<Book> getBook(String bookId);

    List<BookWithShelfStatus> findBookWithShelfStatus(@Param("bookId")String bookId, @Param("uid")String uid);

}
