package com.example.booksystem.core.entity;

import lombok.Data;

@Data
//@AllArgsConstructor
public class User {
    String uid;
    String username;

    String nickname;

    String password;

    String role;

    String avatar;

    String describe;

    String token;
}
