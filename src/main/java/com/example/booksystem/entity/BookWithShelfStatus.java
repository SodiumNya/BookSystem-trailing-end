package com.example.booksystem.entity;

import lombok.Data;

@Data
public class BookWithShelfStatus {
    private Book book;

    private Boolean addedToShelf;
}
