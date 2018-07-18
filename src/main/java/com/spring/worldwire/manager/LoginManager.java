package com.spring.worldwire.manager;

import com.spring.worldwire.model.UserInfo;

import javax.servlet.http.HttpServletResponse;

/**
 * Desc: Desc
 * User: luxun
 * Date: 2018/7/16 17:07
 * To change this template use File | Settings | File and Code Templates | Includes | File Header.
 */
public interface LoginManager {

    public UserInfo login(String email, String password, HttpServletResponse response);

    public UserInfo thirdLogin(String thirdKey, Integer thirdType, HttpServletResponse response);
}
