package com.spring.worldwire.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by luxun on 2018/5/7.
 */
public class HttpUtils {

    public static String getCookieByKey(String key, HttpServletRequest request){
        if(StringUtils.isEmpty(key)){
            return null;
        }
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie:cookies) {
            if(cookie.getName().equals(key)){
                return cookie.getValue();
            }
        }
        return null;
    }
}
