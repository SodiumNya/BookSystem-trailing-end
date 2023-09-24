package com.example.booksystem.expection;

import com.example.booksystem.common.RestBean;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
@ResponseBody

public class GlobalExceptionHandler {
    @ExceptionHandler(value = ServiceException.class)
    public String ServiceException(ServiceException e){
        return RestBean.failure(Integer.parseInt(e.getCode()), e.getMessage()).asJsonString();
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public String SQLException(Exception e){
        return RestBean.failure(405, "插入失败, 数据不合法").asJsonString();
    }
}

