package com.spring.worldwire.controller;

import com.alibaba.fastjson.JSONObject;
import com.spring.worldwire.utils.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by luxun on 2018/4/27.
 */
@RestController
@RequestMapping("/file")
public class FileControler {

    @Value("${file.basedir}")
    private String baseFilePath;

    private static DateFormat df = new SimpleDateFormat("yyyyMMdd");

    @RequestMapping("/upload")
    @ResponseBody
    public JSONObject fileUpload(MultipartFile file, String type) throws IOException {
        JSONObject object = new JSONObject();
        if (StringUtils.isEmpty(type)) {
            type = "default";
        }
        if (!baseFilePath.endsWith(File.separator)) {
            baseFilePath = baseFilePath + File.separator;
        }
        String relativePath = File.separator + type + File.separator + df.format(new Date()) + File.separator + file.getOriginalFilename();
        String fullPath = baseFilePath + relativePath;
        if (FileUtil.upload(file, fullPath)) {
            object.put("path", relativePath);
        }
        return object;
    }
}
