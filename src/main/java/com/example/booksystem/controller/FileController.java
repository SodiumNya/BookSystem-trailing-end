package com.example.booksystem.controller;

import cn.hutool.core.io.FileUtil;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
public class FileController {

    @Value("${server.port}")
    private String serverPort;

    @Value("${server.address}")
    private String serverAddress;
    private static final String ROOT_PATH = System.getProperty("user.dir") + File.separator + "files";

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
}
