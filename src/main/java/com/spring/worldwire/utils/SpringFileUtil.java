package com.spring.worldwire.utils;

import org.springframework.util.ResourceUtils;

import java.io.*;

/**
 * Created by luxun on 2018/4/23.
 */
public class SpringFileUtil {

    public static File getFileFromResource(String path) throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:" + path);
        return file;
    }

    public static String getFileStrFromResource(String path) throws IOException {
        InputStream stream = SpringFileUtil.class.getClassLoader().getResourceAsStream(path);
        byte[] bytes = new byte[stream.available()];
        stream.read(bytes);
        return new String(bytes);
    }

    public static void main(String[] args) throws IOException {
        System.out.println(SpringFileUtil.getFileStrFromResource("static/mail/mail_send_template.html"));
    }
}
