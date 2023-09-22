package com.example.booksystem.mapper;

import com.example.booksystem.core.entity.Book;
import com.example.booksystem.core.entity.BookWithShelfStatus;
import com.example.booksystem.core.vo.SplitPageDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookMapper {

    List<Book> getBookList(String data, Integer start, Integer size);
    List<Book> getBook(String bookId);

    List<BookWithShelfStatus> findBookWithShelfStatus(@Param("bookId")String bookId, @Param("uid")String uid);

    Integer updateBook(@Param("book") Book book);

    Integer deleteBook(String bookId);

    SplitPageDTO getCount(String data);
}
