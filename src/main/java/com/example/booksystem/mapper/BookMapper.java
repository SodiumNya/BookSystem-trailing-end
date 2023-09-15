package com.example.booksystem.mapper;

import com.example.booksystem.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookMapper {

    List<Book> getBookList(String data, Integer start, Integer size);
    List<Book> getBook(String bookId);




}
