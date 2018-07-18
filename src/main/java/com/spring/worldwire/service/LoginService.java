package com.spring.worldwire.service;

import com.spring.worldwire.model.LoginInfo;
import com.spring.worldwire.model.UserInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Desc: Desc
 * User: luxun
 * Date: 2018/5/21 17:19
 * To change this template use File | Settings | File and Code Templates | Includes | File Header.
 */
public interface LoginService {

    public int register(LoginInfo info);

    public UserInfo login(LoginInfo info, HttpServletResponse response, HttpServletRequest request);

    public abstract String auth();

    public abstract UserInfo callback(HttpServletRequest request, HttpServletResponse response);

}
