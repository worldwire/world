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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @Author luxun.
 * @Date 2018/5/8 15:31
 * @Package com.spring.worldwire.controller.login
 */
@RequestMapping("/login/weibo")
@Controller
public class WeiBoLoginController {

    @Autowired
    @Qualifier("weiboService")
    private LoginService loginService;

    private Logger log = LoggerFactory.getLogger(WeiBoLoginController.class);

    @RequestMapping("/auth")
    public void auth(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String result = loginService.auth();
        if (!StringUtils.isEmpty(result)) {
            response.sendRedirect(result);
        }
    }

    @RequestMapping("/callback")
    public String authCallback(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserInfo userInfo = loginService.callback(request, response);
        if (Objects.isNull(userInfo)) {
            return "redirect:login";
        }
        String fromURL = request.getHeader("Referer");
        if(StringUtils.isEmpty(fromURL)){
            fromURL = "/";
        }
        return "redirect:" + fromURL;  //登录成功跳转到登录前页面

    }

}
