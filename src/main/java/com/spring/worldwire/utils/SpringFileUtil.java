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
        File file = ResourceUtils.getFile("classpath:" + path);
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String temp = "";
        while((temp = br.readLine()) != null){
            sb.append(temp);
        }
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        System.out.println(SpringFileUtil.getFileStrFromResource("templates/mail_template.html"));
    }
}
