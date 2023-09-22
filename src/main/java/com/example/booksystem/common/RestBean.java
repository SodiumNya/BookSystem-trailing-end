package com.example.booksystem.common;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONWriter;
import com.example.booksystem.core.vo.SplitPageDTO;
import lombok.Data;

@Data
public class RestBean<T>{
    private int code;
    T data;
    String message;

    private Integer currentPage = 1;

    private Integer pageSize = 10;

    private Integer total = 0;

    private Boolean asc = false;



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

    public RestBean<T> setCurrentPage(Integer currentPage){
        this.currentPage = currentPage;
        return this;
    }

    public RestBean<T> setTotal(Integer total){
        this.total = total;
        return this;
    }

    public RestBean<T> setPageSize(Integer pageSize){
        this.pageSize = pageSize;
        return this;
    }

     public String asJsonString(){
        return JSONObject.toJSONString(this, JSONWriter.Feature.WriteNulls);
     }
}
