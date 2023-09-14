package com.example.booksystem.expection;

import com.example.booksystem.common.RestBean;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    @ExceptionHandler(ServiceException.class)
    public String ServiceException(ServiceException e){
        return RestBean.failure(Integer.parseInt(e.getCode()), e.getMessage()).asJsonString();
    }
}

