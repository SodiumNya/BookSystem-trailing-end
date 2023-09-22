package com.example.booksystem;

import com.example.booksystem.core.entity.Book;
import com.example.booksystem.service.BookService;
import jakarta.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceTest {

    @Resource
    BookService bookService;

}
