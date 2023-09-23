package com.example.booksystem.controller;

import cn.hutool.core.util.StrUtil;
import com.example.booksystem.common.RestBean;
import com.example.booksystem.core.entity.User;
import com.example.booksystem.core.request.Result;
import com.example.booksystem.service.AdminService;
import jakarta.annotation.Resource;
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

        Result<List<User>> res = adminService.getUsersInfo(selectedRole, searchInput, currentPage, PageSize);



        return RestBean.success(res.getData())
                .setCurrentPage(res.getSplitPageDTO().getCurrentPage())
                .setPageSize(res.getSplitPageDTO().getPageSize())
                .setTotal(res.getSplitPageDTO().getTotal())
                .asJsonString();
    }

    @GetMapping("api/admin/user/reSet/avatar{uid}")
    public String reSetAvatar(@PathVariable String uid){
        if(StrUtil.isBlank(uid)){
            return RestBean.failure(401, "参数请求错误").asJsonString();
        }
        String url = adminService.reSetAvatar(uid);

        return RestBean.success(url).asJsonString();
    }

    @DeleteMapping("api/delete/user/{uid}")
    public String deleteUser(@PathVariable String uid){
        if(StrUtil.isBlank(uid)){
            return RestBean.failure(401, "参数请求错误").asJsonString();
        }

        adminService.deleteUser(uid);
        return RestBean.success("删除成功！").asJsonString();
    }

}
