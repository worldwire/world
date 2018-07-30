package com.spring.worldwire.controller.platform;

import com.spring.worldwire.constants.Constants;
import com.spring.worldwire.model.AdminUser;
import com.spring.worldwire.service.AdminUserService;
import com.spring.worldwire.utils.LayuiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Auther pg
 * @Date create in 17:36 2018/7/29
 */
@SuppressWarnings("unused")
@Controller()
@RequestMapping("/adminUser")
public class AdminUserController {
    @Autowired
    private AdminUserService adminUserService;

    @RequestMapping("login")
    @ResponseBody
    public String login(AdminUser adminUser, HttpServletRequest request) {
        adminUser = adminUserService.findAdmin(adminUser);
        if (adminUser != null) {
            request.getSession().setAttribute(Constants.ADMIN_USER_SESSION, adminUser);
            return LayuiResult.sussceResult();
        } else {
            return LayuiResult.errResult("用户名或者密码错误");
        }

    }

    @RequestMapping("register")
    @ResponseBody
    public String register(AdminUser adminUser) {
        adminUser.setStatus(1);
        int save = adminUserService.save(adminUser);
        if (save == 1) {
            return LayuiResult.sussceResult();
        } else {
            return LayuiResult.errResult("用户名重复或者已存在");
        }
    }

    @RequestMapping("toList")
    public String toList() {
        return "platform/adminUser";
    }

    @RequestMapping("list")
    @ResponseBody
    public String list(AdminUser adminUser) {
        List<AdminUser> list = adminUserService.findByUser(adminUser);
        return LayuiResult.formatResult(list);
    }

    @RequestMapping("del")
    @ResponseBody
    public String del(long id, HttpServletRequest request) {
        AdminUser adminUser = (AdminUser) request.getSession().getAttribute(Constants.ADMIN_USER_SESSION);
        if (adminUser == null || !"admin".equalsIgnoreCase(adminUser.getUserName())) {
            return LayuiResult.errResult("权限不足");
        }
        adminUserService.del(id);
        return LayuiResult.sussceResult();
    }


    @RequestMapping("adopt")
    @ResponseBody
    public String adopt(long id, HttpServletRequest request) {

        AdminUser adminUser = (AdminUser) request.getSession().getAttribute(Constants.ADMIN_USER_SESSION);
        if (adminUser == null || !"admin".equalsIgnoreCase(adminUser.getUserName())) {
            return LayuiResult.errResult("权限不足");
        }
        adminUserService.adopt(id);
        return LayuiResult.sussceResult();
    }

}
