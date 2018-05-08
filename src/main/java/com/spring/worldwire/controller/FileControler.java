package com.spring.worldwire.controller;

import com.spring.worldwire.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Created by luxun on 2018/4/27.
 */
@RestController
@RequestMapping("/file")
public class FileControler{

    @Value("${file.basedir}")
    private String baseFilePath;

    @RequestMapping("/upload")
    public String fileUpload(MultipartFile file,String type) throws IOException {
        if(StringUtils.isEmpty(type)){
            type = "default";
        }
        if(!baseFilePath.endsWith("\\")){
            baseFilePath = baseFilePath + File.separator;
        }
        String relativePath = type + File.separator + new Date().getTime() + File.separator + file.getOriginalFilename();
        String fullPath = baseFilePath + relativePath;
        if(FileUtil.upload(file,fullPath)){
            return relativePath;
        }
        return "";
    }
}
