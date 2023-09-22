package com.example.booksystem.service;

import com.example.booksystem.core.entity.User;
import com.example.booksystem.core.request.Result;
import com.example.booksystem.core.vo.SplitPageDTO;
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

    public Result<List<User>> getUsersInfo(
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

        SplitPageDTO splitPageDTO = adminMapper.getCount(selectRole, searchInput);

        splitPageDTO.setCurrentPage(Integer.parseInt(currentPage));
        splitPageDTO.setPageSize(Integer.parseInt(PageSize));

        Result<List<User>> result = new Result<>();
        result.setData(usersInfo);
        result.setSplitPageDTO(splitPageDTO);
        return result;
    }

    public String reSetAvatar(String uid){
        return "http://localhost:9787/files/download/1695021417059_ddd.webp";
    }

    public void deleteUser(String uid){
        Integer res = adminMapper.deleteUser(uid);
        if(res < 0){
            throw new ServiceException("删除失败, 请重试");
        }
    }
}
