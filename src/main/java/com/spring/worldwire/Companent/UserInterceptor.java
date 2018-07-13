package com.spring.worldwire.Companent;

import com.spring.worldwire.constants.Constants;
import com.spring.worldwire.model.UserInfo;
import com.spring.worldwire.service.UserInfoService;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Auther pg
 * @Date create in 22:26 2018/7/13
 */
public class UserInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) {
        Cookie[] cookies = httpServletRequest.getCookies();
        //Arrays.stream(cookies).forEach(cookie -> setCookie(httpServletRequest,cookie));

        return true;
    }

    private void setCookie(HttpServletRequest httpServletRequest, Cookie cookie) {
        HttpSession session = httpServletRequest.getSession();
        if(Constants.USER_COOKIES_NAME.equalsIgnoreCase(cookie.getName())) {
            UserInfo userInfo = new UserInfo();
            userInfo.setId(Long.parseLong(cookie.getValue()));
            session.setAttribute(Constants.USER_SESSION, userInfo);
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
