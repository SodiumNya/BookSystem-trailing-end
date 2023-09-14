package com.example.booksystem.expection;


import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException{
    String code;
    public ServiceException(String msg){
        super(msg);
    }

    public ServiceException(String code, String msg){
        super(msg);
        this.code = code;
    }
}

