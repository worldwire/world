package com.spring.worldwire.config;

import com.spring.worldwire.model.LoginInfo;
import com.spring.worldwire.service.LoginInfoService;
import com.spring.worldwire.service.UserInfoService;
import com.spring.worldwire.utils.Base64;
import com.spring.worldwire.utils.HttpUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * Desc: Desc
 * User: luxun
 * Date: 2018/7/11 13:57
 * To change this template use File | Settings | File and Code Templates | Includes | File Header.
 */
public class InterceptorConfig implements HandlerInterceptor {

    private static final String COOKIE_KEY = "user";

    @Autowired
    private LoginInfoService loginInfoService;
    @Autowired
    private UserInfoService userInfoService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        String encruptedCookie = HttpUtils.getCookieByKey(COOKIE_KEY, httpServletRequest);
        String originValue = new String(Base64.decode(encruptedCookie));
        String[] array = originValue.split("_");
        if (array.length == 2 && NumberUtils.isNumber(array[1])) {
            Long userId = Long.parseLong(array[1]);
            LoginInfo loginInfo = loginInfoService.selectByPrimaryKey(userId);
            if (Objects.nonNull(loginInfo) && Objects.equals(loginInfo.getUserName(), array[0])) {
                HttpSession session = httpServletRequest.getSession();
                if (session.getAttribute("userId") == null || session.getAttribute("userId") != userId) {
                    session.setAttribute("userId", userId);
                    session.setAttribute("loginInfo", loginInfo);
                    session.setAttribute("userInfo", userInfoService.selectByLoginId(userId));
                }
            }
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
