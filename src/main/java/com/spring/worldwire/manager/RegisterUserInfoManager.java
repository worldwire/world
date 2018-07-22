package com.spring.worldwire.manager;

import com.spring.worldwire.enums.UserTypeEnum;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther pg
 * @Date create in 19:56 2018/7/22
 */
public interface RegisterUserInfoManager {
    void registerUserType(HttpServletRequest request, HttpServletResponse response, UserTypeEnum nameByCode);

    void registerUserForeign(long l, int type);
}
