package com.example.booksystem.service;

import com.example.booksystem.entity.User;
import com.example.booksystem.expection.ServiceException;
import com.example.booksystem.mapper.AdminMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Resource
    AdminMapper adminMapper;

    public void updateUsersInfo(User user){
        Integer res = adminMapper.updateUsersInfo(user);
        if(res < 0){
            throw new ServiceException("500", "更新失败");
        }
    }

    public List<User> getUsersInfo(
            String selectRole,
            String searchInput,
            String currentPage,
            String PageSize){

        Integer start = (Integer.parseInt(currentPage) - 1) * Integer.parseInt(PageSize);
        Integer size = Integer.parseInt(PageSize);

        List<User> usersInfo = adminMapper.getUsersInfo(selectRole, searchInput, start, size);
        if(usersInfo.isEmpty()){
            throw new ServiceException("233", "未查找到内容");
        }
        return usersInfo;
    }

    public String reSetAvatar(String uid){
        return "http://localhost:9787/files/download/1695021417059_ddd.webp";
    }
}
