package com.example.booksystem.common;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONWriter;
import lombok.Data;

@Data
public class RestBean<T> {
    private int code;
    T data;
    String message;

    public RestBean(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public static <T> RestBean<T> success(T data){
        return new RestBean<>(200, data, "获取成功");
    }

    public static <T> RestBean<T> failure(int code, String message){
        return new RestBean<>(code, null, message);
    }

     public String asJsonString(){
        return JSONObject.toJSONString(this, JSONWriter.Feature.WriteNulls);
     }
}
