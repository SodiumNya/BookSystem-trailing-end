package com.example.booksystem.service;

import cn.hutool.core.io.FileUtil;
import com.example.booksystem.entity.Book;
import com.example.booksystem.entity.User;
import com.example.booksystem.expection.ServiceException;
import com.example.booksystem.mapper.UserMapper;
import com.example.booksystem.uitls.TokenUtils;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {


    @Value("${server.port}")
    private String serverPort;

    @Value("${server.address}")
    private String serverAddress;
    private static final String ROOT_PATH = System.getProperty("user.dir") + File.separator + "files";

    @Resource
    UserMapper userMapper;

    public Integer insertUser(User user){

       Integer res = userMapper.insertUser(user);
       System.out.println("返回结果"+res);
       return res;
    }

    public User getUserById(String uid){
        List<User> list = userMapper.getUserById(uid);
        if(list.isEmpty()){
            throw new ServiceException("500", "未知错误");
        }

        return list.get(0);

    }

    public Integer updateBasicInfo(User user){
        List<User> list = userMapper.getUserById(user.getUid());
        if(list.isEmpty()){
            return -1;
        }
        return userMapper.updateBasicInfo(user);
    }

    public Integer updateCoreInfo(User user){
        List<User> list = userMapper.getUserById(user.getUid());
        if(list.isEmpty()){
            return -1;
        }
        return userMapper.updateCoreInfo(user);
    }

    public User reLoadUserinfo(String uid){
        List<User> list = userMapper.getUserById(uid);
        if(list.isEmpty()){
            throw new ServiceException("500", "未知错误");
        }
        User user = list.get(0);
        String token = TokenUtils.generateToken(user.getUid(), user.getPassword());
        user.setToken(token);
        return user;
    }

    public Integer addBookToBookShelf(String bookId, String uid){
        Integer res = userMapper.addBookToBookShelf(bookId, uid);

        if(res < 0){
            throw new ServiceException("401", "请求参数错误");
        }
        return res;
    }

    public Integer removeBookFromBookShelf(String bookId, String uid){
        Integer res = userMapper.removeBookFromBookShelf(bookId, uid);

        if(res < 0){
            throw new ServiceException("401", "请求参数错误");
        }
        return res;
    }

    public List<List<Book>> findBookFromBookShelf(String uid, String currentPage, String PageSize){
        Integer start = (Integer.parseInt(currentPage) - 1) * Integer.parseInt(PageSize);
        Integer size = Integer.parseInt(PageSize);

        List<Book> books = userMapper.findBookFromBookShelf(uid, start, size);
        List<List<Book>> bookData = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            List<Book> bookRow = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                if(books.size()-1 < i*5+j){
                    bookData.add(bookRow);
                    return bookData;
                }
                bookRow.add(books.get(i*5 + j));
            }
            bookData.add(bookRow);
        }
        return bookData;
    }

    public String updateUserAvatar(MultipartFile file, String uid) throws IOException {
        String originFileName = file.getOriginalFilename();
        String mainName = FileUtil.mainName(originFileName);
        String extName = FileUtil.extName(originFileName);

        if(!FileUtil.exist(ROOT_PATH)){
            FileUtil.mkdir(ROOT_PATH);
        }

        if(FileUtil.exist(ROOT_PATH + File.separator + originFileName)) {
            originFileName = System.currentTimeMillis() + "_" + mainName + "." + extName;
        }

        File saveFile = new File(ROOT_PATH + File.separator + originFileName);
        file.transferTo(saveFile);

        String url = "http://" + serverAddress + ":" + serverPort + "/files/download/" + originFileName;

        Integer res = userMapper.updateUserAvatar(url, uid);
        if(res < 0){
            throw new ServiceException("401", "更新失败");
        }
        return url;
    }
}
