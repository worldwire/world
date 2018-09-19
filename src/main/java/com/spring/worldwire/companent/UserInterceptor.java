package com.spring.worldwire.companent;

import com.spring.worldwire.constants.Constants;
import com.spring.worldwire.model.UserInfo;
import java.util.Arrays;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.spring.worldwire.service.UserInfoService;
import com.spring.worldwire.utils.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Auther pg
 * @Date create in 22:26 2018/7/13
 */
public class UserInterceptor implements HandlerInterceptor {

    @Autowired
    private UserInfoService userInfoService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) {
        Cookie[] cookies = httpServletRequest.getCookies();
        if(cookies!=null) {
            Arrays.stream(cookies).forEach(cookie -> setCookie(httpServletRequest, cookie));
        }
        return true;
    }

    private void setCookie(HttpServletRequest httpServletRequest, Cookie cookie) {
        if(Constants.USER_COOKIES_NAME.equalsIgnoreCase(cookie.getName())) {
            UserInfo userInfo = new UserInfo();
            userInfo.analysisCookiesValue(new String(Base64.decode(cookie.getValue())));
            if(userInfo.getId()!=null) {
                httpServletRequest.setAttribute(Constants.USER_ID_SESSION, userInfo.getId());
                UserInfo dbInfo = userInfoService.selectById(userInfo.getId());
                httpServletRequest.setAttribute(Constants.USER_SESSION, dbInfo);
            }
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
