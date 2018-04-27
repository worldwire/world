package com.spring.worldwire.utils;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * Created by luxun on 2018/4/27.
 */
public class FileUtil {

    public static Boolean upload(MultipartFile file, String destination) {
        if(Objects.isNull(file)){
            return false;
        }
        if(!new File(destination).getParentFile().exists()){
            new File(destination).getParentFile().mkdirs();
        }
        File descFile = new File(destination);
        try {
            file.transferTo(descFile);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
