package com.example.booksystem.service;

import cn.hutool.core.io.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FileService {


    @Value("${server.port}")
    private String serverPort;

    @Value("${server.address}")
    private String serverAddress;
    private static final String ROOT_PATH = System.getProperty("user.dir") + File.separator + "files";
    public String upload(MultipartFile file) throws IOException {
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

        return "http://" + serverAddress + ":" + serverPort + "/files/download/" + originFileName;
    }
}
