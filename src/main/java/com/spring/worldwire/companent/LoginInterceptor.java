package com.spring.worldwire.companent;

import com.spring.worldwire.constants.Constants;
import com.spring.worldwire.model.UserInfo;
import com.spring.worldwire.utils.Base64;
import com.spring.worldwire.utils.HttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * Desc: Desc
 * User: luxun
 * Date: 2018/7/19 15:54
 * To change this template use File | Settings | File and Code Templates | Includes | File Header.
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String encruptedCookie = HttpUtils.getCookieByKey(Constants.USER_ID_SESSION, httpServletRequest);
        if (StringUtils.isEmpty(encruptedCookie)) {
            httpServletResponse.sendRedirect("/login");
        }
        try {
            UserInfo userInfo = new UserInfo();
            userInfo.analysisCookiesValue(new String(Base64.decode(encruptedCookie)));
            if (Objects.isNull(userInfo.getId())) {
                httpServletResponse.sendRedirect("/login");
            }
        } catch (Exception e) {
            httpServletResponse.sendRedirect("/login");
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
