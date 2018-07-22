package com.spring.worldwire.manager.impl;

import com.spring.worldwire.constants.Constants;
import com.spring.worldwire.model.UserInfo;
import com.spring.worldwire.utils.Base64;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther pg
 * @Date create in 20:01 2018/7/22
 */
public class CookieManager {


    /**
     * 登陆之后处理cookie
     *
     * @param response
     * @param userInfo
     */
    void processCookie(HttpServletResponse response, UserInfo userInfo) {

        String encryptedCookie = Base64.encode(userInfo.cookiesValue().getBytes());
        Cookie userIdCookie = new Cookie(Constants.USER_COOKIES_NAME, encryptedCookie);
        userIdCookie.setPath("/");
        userIdCookie.setMaxAge(3600);

        response.addCookie(userIdCookie);
    }
}
