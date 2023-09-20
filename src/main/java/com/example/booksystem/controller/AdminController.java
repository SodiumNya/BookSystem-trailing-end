package com.example.booksystem.controller;

import cn.hutool.core.util.StrUtil;
import com.example.booksystem.common.RestBean;
import com.example.booksystem.entity.User;
import com.example.booksystem.service.AdminService;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class AdminController {

    @Resource
    AdminService adminService;
    @PostMapping("/api/admin/manage/user/update")
    public String updateUsersInfo(@RequestBody User user){
        if(user == null){
            return RestBean.failure(401, "参数请求错误").asJsonString();
        }

        adminService.updateUsersInfo(user);

        return RestBean.success("更新成功！").asJsonString();
    }

    @GetMapping("api/admin/user/get/all/{selectedRole}/{searchInput}/{currentPage}/{PageSize}")
    public String getUsersInfo(
            @PathVariable String selectedRole,
            @PathVariable String searchInput,
            @PathVariable String currentPage,
            @PathVariable String PageSize){

        if((StrUtil.isBlank(selectedRole) && StrUtil.isBlank(searchInput)) ||
                StrUtil.isBlank(currentPage) || StrUtil.isBlank(PageSize) ){
            return RestBean.failure(401, "参数请求错误").asJsonString();
        }
        List<User> users = adminService.getUsersInfo(selectedRole, searchInput, currentPage, PageSize);
        return RestBean.success(users).asJsonString();
    }
}
