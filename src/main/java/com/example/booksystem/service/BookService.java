package com.example.booksystem.service;

import com.example.booksystem.entity.Book;
import com.example.booksystem.entity.BookWithShelfStatus;
import com.example.booksystem.expection.ServiceException;
import com.example.booksystem.mapper.BookMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    @Resource
    BookMapper bookMapper;

    public List<List<Book>> getBookList(String data, String currentPage, String PageSize){
        Integer start = (Integer.parseInt(currentPage) - 1) * Integer.parseInt(PageSize);
        Integer size = Integer.parseInt(PageSize);
        List<Book> books = bookMapper.getBookList(data, start, size);

        if(books.isEmpty()){
            throw new ServiceException("401", "没有找到书");
        }

        List<List<Book>> bookData = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            List<Book> bookRow = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                if(books.size()-1 < i*5+j){
                    bookData.add(bookRow);
                    return bookData;
                }
                bookRow.add(books.get(i*5 + j));
            }
            bookData.add(bookRow);
        }

        return bookData;
    }

    public List<Book> getBook(String bookId){

        List<Book> books = bookMapper.getBook(bookId);

        if(books.isEmpty()){
            throw new ServiceException("401", "没有找到书");
        }
        return books;
    }

    public List<BookWithShelfStatus> findBookWithShelfStatus(String bookId, String uid){
        List<BookWithShelfStatus> bookWithShelfStatusList = bookMapper.findBookWithShelfStatus(bookId, uid);

        if(bookWithShelfStatusList.isEmpty()){
            throw new ServiceException("401", "没有找到书");
        }
        return bookWithShelfStatusList;
    }
}
