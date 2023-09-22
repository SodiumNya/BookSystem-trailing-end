package com.example.booksystem.controller;


import cn.hutool.core.util.StrUtil;
import com.example.booksystem.common.RestBean;
import com.example.booksystem.core.entity.Book;
import com.example.booksystem.core.entity.User;
import com.example.booksystem.expection.ServiceException;
import com.example.booksystem.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseBody
public class UserController {


    @Resource
    private UserService userService;

    @PostMapping("/api/add/user")
    public String insertUser(@RequestBody User user) {

        Integer res = userService.insertUser(user);
        if (res >= 0) {
            return RestBean.success("success").asJsonString();
        }

        return RestBean.failure(401, "同名").asJsonString();


    }

    @PostMapping("/api/update/basic")
    public String updateBasicInfo(@RequestBody User user){
        Integer res = userService.updateBasicInfo(user);
        if(res >= 0){
            return RestBean.success("success").asJsonString();
        }

        return RestBean.failure(401, "修改失败").asJsonString();
    }

    @PostMapping("/api/update/core")
    public String updateCoreInfo(@RequestBody User user){
        Integer res = userService.updateCoreInfo(user);
        if(res >= 0){
            return RestBean.success("success").asJsonString();
        }

        return RestBean.failure(401, "修改失败").asJsonString();
    }

    @GetMapping("/api/select/{uid}")
    public String reLoadUserinfo(@PathVariable String uid){
        User users = userService.reLoadUserinfo(uid);
        if(users != null){
            return RestBean.success(users).asJsonString();
        }
        return RestBean.failure(401, "获取失败").asJsonString();
    }

    @PostMapping("api/add/bookShelf/{bookId}/{uid}")
    public String addBookToBookShelf(@PathVariable String bookId, @PathVariable String uid){
        Integer res = userService.addBookToBookShelf(bookId, uid);
        return RestBean.success("已经加入到书架，快去查看吧！").asJsonString();

    }

    @PostMapping("api/remove/bookShelf/{bookId}/{uid}")
    public String removeBookFromBookShelf(@PathVariable String bookId, @PathVariable String uid){
        if(StrUtil.isBlank(uid) || StrUtil.isBlank(bookId)){
            throw new ServiceException("参数不合法");
        }
        Integer res = userService.removeBookFromBookShelf(bookId, uid);
        return RestBean.success("移出书架成功").asJsonString();
    }

    @GetMapping("api/select/bookShelf{uid}/{currentPage}/{PageSize}")
    public String findBookFromBookShelf(@PathVariable String uid, @PathVariable String currentPage, @PathVariable String PageSize){
        if(StrUtil.isBlank(uid) || StrUtil.isBlank(currentPage) || StrUtil.isBlank(PageSize)){
            throw new ServiceException("参数不合法");
        }
        List<List<Book>> books = userService.findBookFromBookShelf(uid, currentPage, PageSize);

        return RestBean.success(books).asJsonString();
    }
}