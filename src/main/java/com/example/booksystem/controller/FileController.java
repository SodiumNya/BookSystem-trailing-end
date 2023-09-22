package com.example.booksystem.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.example.booksystem.common.RestBean;
import com.example.booksystem.expection.ServiceException;
import com.example.booksystem.service.FileService;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@ResponseBody
public class FileController {

    private static final String ROOT_PATH = System.getProperty("user.dir") + File.separator + "files";

    @Resource
    FileService fileService;

    @GetMapping("/files/download/{filename}")
    public void download(@PathVariable String filename, HttpServletResponse resp) throws IOException {
        resp.addHeader("Content-Disposition", "inline;filename="+ URLEncoder.encode(filename, StandardCharsets.UTF_8));
        String filePath = ROOT_PATH + File.separator + filename;
        if(!FileUtil.exist(filePath)) {
            return;
        }
        byte[] bytes = FileUtil.readBytes(filePath);
        ServletOutputStream outputStream = resp.getOutputStream();
        outputStream.write(bytes);
        outputStream.flush();
        outputStream.close();
    }

    @PostMapping("api/files/upload")
    public String upload(@RequestPart("file") MultipartFile file) throws IOException {
        if ((file == null || file.isEmpty())){
            throw new ServiceException("参数不合法");
        }
        String url = fileService.upload(file);

        if(StrUtil.isBlank(url)){
            return RestBean.failure(401, "上传失败，请重试").asJsonString();
        }
        return RestBean.success(url).asJsonString();

    }
}
