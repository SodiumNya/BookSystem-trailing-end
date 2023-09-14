package com.example.booksystem.entity;

import lombok.Data;

@Data
//@AllArgsConstructor
public class User {
    String uid;
    String username;

    String nickname;

    String password;

    String role;

    String token;
}
