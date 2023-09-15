package com.example.booksystem.controller;

import cn.hutool.core.util.StrUtil;
import com.example.booksystem.common.RestBean;
import com.example.booksystem.entity.Book;
import com.example.booksystem.service.BookService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@ResponseBody
public class BookController {
    @Resource
    BookService bookService;
    @PostMapping(value = "api/select/bookList/{data}/{currentPage}/{pageSize}", produces = { "text/html;charset=utf-8" })
    public String getBookList(@PathVariable String data, @PathVariable String currentPage, @PathVariable String pageSize){
        if(StrUtil.isBlank(data) || StrUtil.isBlank(currentPage) || StrUtil.isBlank(pageSize)){
            return RestBean.failure(401, "请求参数错误").asJsonString();
        }

        List<List<Book>> books = bookService.getBookList(data, currentPage, pageSize);

        return RestBean.success(books).asJsonString();
    }

    @GetMapping(value = "api/select/book/{bookId}", produces = { "text/html;charset=utf-8" })
    public String getBook(@PathVariable String bookId){
        if(StrUtil.isBlank(bookId)){
            return RestBean.failure(401, "请求参数错误").asJsonString();
        }

        List<Book> book = bookService.getBook(bookId);

        return RestBean.success(book.get(0)).asJsonString();
    }
}
