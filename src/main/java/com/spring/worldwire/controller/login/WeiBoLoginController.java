package com.spring.worldwire.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import weibo4j.Oauth;
import weibo4j.Users;
import weibo4j.model.User;
import weibo4j.util.BareBonesBrowserLaunch;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author luxun.
 * @Date 2018/5/8 15:31
 * @Package com.spring.worldwire.controller.login
 */
@RequestMapping("/login/weibo")
@Controller
public class WeiBoLoginController {

    @RequestMapping("/auth")
    public ModelAndView auth(HttpServletRequest request,
                             HttpServletResponse response) throws Exception {
        Oauth oauth = new Oauth();
        String url = oauth.authorize("code", null);
        response.sendRedirect(url);
//      BareBonesBrowserLaunch.openURL(oauth.authorize("code", null));
        return null;
    }

    @RequestMapping("/callback")
    public ModelAndView authCallback(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String code = request.getParameter("code");
        Oauth oauth = new Oauth();
        String token = "";
        try {
            token = oauth.getAccessTokenByCode(code).toString();
            String str[] = token.split(",");
            String accessToken = str[0].split("=")[1];
            String str1[] = str[3].split("]");
            String uid = str1[0].split("=")[1];
            Users users = new Users();
            users.client.setToken(accessToken);
            User weiboUser = users.showUserById(uid);
            String name = weiboUser.getScreenName();
            request.getSession().setAttribute("user", name);
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        response.sendRedirect(request.getContextPath() + "/index.do");
        return null;
    }

}
