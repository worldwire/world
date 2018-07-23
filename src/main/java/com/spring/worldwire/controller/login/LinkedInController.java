package com.spring.worldwire.controller.login;

import com.spring.worldwire.model.LoginInfo;
import com.spring.worldwire.model.UserInfo;
import com.spring.worldwire.service.LoginService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @Author luxun.
 * @Date 2018/5/8 17:14
 * @Package com.spring.worldwire.controller.login
 */
@Controller
@RequestMapping("/login/linkedin")
public class LinkedInController {

    @Autowired
    @Qualifier("linkedInService")
    private LoginService loginService;


    private static Logger log = LoggerFactory.getLogger(LinkedInController.class);

    @RequestMapping(value = "/auth")
    public void auth(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String result = loginService.auth();
        if (!StringUtils.isEmpty(result)) {
            response.sendRedirect(result);
        }
    }

    @RequestMapping(value = "/callback")
    public String callback(HttpServletRequest request, HttpServletResponse response) {
        UserInfo userInfo = loginService.callback(request, response);
        if (Objects.isNull(userInfo)) {
            return "redirect:login";
        }
        return "redirect:/index";  //登录成功跳转到登录前页面
    }


}
