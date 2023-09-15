package com.example.booksystem;

import com.example.booksystem.entity.Book;
import com.example.booksystem.service.BookService;
import jakarta.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceTest {

    @Resource
    BookService bookService;

    @Test
    public void getBookTest(){
        String s = "刘慈欣";
        List<List<Book>> books = bookService.getBookList(s, "1", "5");
        books.forEach(System.out::println);

    }
}
