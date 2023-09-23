package com.example.booksystem.service;

import com.example.booksystem.core.entity.Book;
import com.example.booksystem.core.entity.BookWithShelfStatus;
import com.example.booksystem.core.request.Result;
import com.example.booksystem.core.vo.SplitPageDTO;
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

    public Result<List<List<Book>>> getBookList(String data, String currentPage, String PageSize){
        Integer start = (Integer.parseInt(currentPage) - 1) * Integer.parseInt(PageSize);
        Integer size = Integer.parseInt(PageSize);
        List<Book> books = bookMapper.getBookList(data, start, size);

        if(books.isEmpty()){
            throw new ServiceException("401", "没有找到书");
        }

        List<List<Book>> bookData = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            List<Book> bookRow = new ArrayList<>();
            boolean end = false;
            for (int j = 0; j < 5; j++) {
                if(books.size()-1 < i*5+j){
                    bookData.add(bookRow);
                    end = true;
                    break;
                }
                bookRow.add(books.get(i*5 + j));
            }
            if(end){
                break;
            }
            bookData.add(bookRow);
        }

        SplitPageDTO splitPageDTO = bookMapper.getCount(data);

        splitPageDTO.setPageSize(Integer.parseInt(PageSize));
        splitPageDTO.setCurrentPage(Integer.parseInt(currentPage));

        Result<List<List<Book>>> res = new Result<>();
        res.setSplitPageDTO(splitPageDTO);
        res.setData(bookData);
        return res;
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

    public Book updateBook(Book book){
        Integer res = bookMapper.updateBook(book);
        if(res < 0){
            throw new ServiceException("更新失败，请重试");
        }
        List<Book> newBooks = bookMapper.getBook(book.getBookId());

        if(newBooks.size() != 1) {
            throw new ServiceException("服务器内部错误, 请稍后重试");
        }
        return newBooks.get(0);

    }

    public void deleteBook(String bookID){

        Integer res = bookMapper.deleteBook(bookID);

        if(res < 0){
            throw new ServiceException("删除失败, 请重试");
        }

    }

    public void addBook(Book book){

        List<Book> bookList = bookMapper.findBookByTitleAndAuthor(book.getBookAuthor(), book.getBookTitle());
        if(!bookList.isEmpty()){
            throw new ServiceException("402", "该书已存在书库, 请勿重复添加");
        }

        List<String> authors = bookMapper.findBookAuthorByName(book.getBookAuthor());
        if(authors.isEmpty()){
           Integer res = bookMapper.addAuthor(book.getBookAuthor());
           if(res < 0){
               throw new ServiceException("500", "内部错误, 请等会试试");
           }
        }

        Integer res = bookMapper.addBook(book);

        if(res < 0){
            throw new ServiceException("添加, 请重试");
        }
    }

}
