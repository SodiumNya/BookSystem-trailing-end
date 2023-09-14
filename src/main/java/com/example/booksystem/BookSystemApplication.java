package com.example.booksystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com/example/booksystem/mapper")

public class BookSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookSystemApplication.class, args);
    }
}
