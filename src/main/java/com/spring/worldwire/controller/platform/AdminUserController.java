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
    public String login(AdminUser adminUser, HttpServletRequest request){
        adminUser = adminUserService.findAdmin(adminUser);

        request.getSession().setAttribute(Constants.ADMIN_USER_SESSION,adminUser);

        return "redirect:/platform/index";
    }

    @RequestMapping("register")
    public String register(AdminUser adminUser){
        adminUser.setStatus(1);
        adminUserService.save(adminUser);
        return "redirect:/platform/toLogin";
    }

    @RequestMapping("toList")
    public String toList(){
        return "platform/adminUser";
    }

    @RequestMapping("list")
    @ResponseBody
    public String list(AdminUser adminUser){
        List<AdminUser> list = adminUserService.findByUser(adminUser);
        return LayuiResult.formatResult(list);
    }

}
