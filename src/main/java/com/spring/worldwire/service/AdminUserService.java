package com.spring.worldwire.service;

import com.spring.worldwire.model.AdminUser;
import com.spring.worldwire.model.LoginInfo;
import com.spring.worldwire.model.UserInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Desc: Desc
 * User: luxun
 * Date: 2018/5/21 17:19
 * To change this template use File | Settings | File and Code Templates | Includes | File Header.
 */
public interface AdminUserService {


    AdminUser findAdmin(AdminUser adminUser);

    int save(AdminUser adminUser);

    List<AdminUser> findByUser(AdminUser adminUser);
}
