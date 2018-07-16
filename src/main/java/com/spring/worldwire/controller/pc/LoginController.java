package com.spring.worldwire.controller.pc;

import com.alibaba.fastjson.JSONObject;
import com.spring.worldwire.constants.Constants;
import com.spring.worldwire.model.LoginInfo;
import com.spring.worldwire.model.UserInfo;
import com.spring.worldwire.query.LoginInfoQuery;
import com.spring.worldwire.service.LoginInfoService;
import com.spring.worldwire.service.UserInfoService;
import com.spring.worldwire.utils.DateUtil;
import com.spring.worldwire.utils.RedisUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Desc: Desc
 * User: luxun
 * Date: 2018/7/11 13:54
 * To change this template use File | Settings | File and Code Templates | Includes | File Header.
 */
@SuppressWarnings("unused")
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginInfoService loginInfoService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private RedisUtils redisUtils;

    public LoginController(LoginInfoService loginInfoService, UserInfoService userInfoService) {
        this.loginInfoService = loginInfoService;
        this.userInfoService = userInfoService;
    }

    @RequestMapping("/")
    public String toLogin(HttpServletRequest request) {
        if (request.getSession().getAttribute("userid") != null) {
            return "redirect:" + request.getHeader("Referer");
        }
        return "pc/login";
    }

    @RequestMapping("/login")
    public String login(HttpServletResponse response, HttpServletRequest request, String email, String password) {
        LoginInfoQuery query = new LoginInfoQuery();
        query.setEmail(email);
        query.setPassword(password);
        List<LoginInfo> list = loginInfoService.selectByQuery(query);
        if (CollectionUtils.isEmpty(list)) {
            return "pc/blank";
        }

        LoginInfo loginInfo = list.get(0);

        UserInfo userInfo = userInfoService.selectByLoginId(loginInfo.getId());

        Cookie userIdCookie = new Cookie(Constants.USER_COOKIES_NAME, userInfo.cookiesValue());
        userIdCookie.setPath("/");
        userIdCookie.setMaxAge(3600);

        response.addCookie(userIdCookie);
        request.getSession().setAttribute("userInfo", userInfo);

        if (userInfo.getType() == null) {
            return "pc/registerAfter";
        }

        return "pc/blank";
    }

    @RequestMapping("/ajaxLogin")
    @ResponseBody
    public String ajaxLogin(String email, String password, HttpServletRequest request, HttpServletResponse response) {
        LoginInfoQuery query = new LoginInfoQuery();
        query.setEmail(email);
        query.setPassword(password);
        List<LoginInfo> list = loginInfoService.selectByQuery(query);
        if (!CollectionUtils.isEmpty(list)) {
            LoginInfo loginInfo = list.get(0);
            UserInfo userInfo = userInfoService.selectByLoginId(loginInfo.getId());

            Cookie userIdCookie = new Cookie(Constants.USER_COOKIES_NAME, userInfo.cookiesValue());
            userIdCookie.setPath("/");
            userIdCookie.setMaxAge(3600);

            response.addCookie(userIdCookie);
            request.getSession().setAttribute("userInfo", userInfo);
            return JSONObject.toJSONString(list.get(0));
        }
        return null;
    }

    @RequestMapping("/showContracts")
    @ResponseBody
    public String showContracts(Long loginId, Long productRequestId) {
        if (Objects.isNull(loginId) || NumberUtils.isNumber(loginId.toString())) {
            return "error";
        }
        if (redisUtils.isCacheExists(Constants.CACHE_FREE_LOOK_UP + loginId)) {
            return "false";
        }

        UserInfo info = userInfoService.selectByLoginId(loginId);
        long remains = DateUtil.getTimeInterval(new Date());
        redisUtils.set(Constants.CACHE_FREE_LOOK_UP + loginId, productRequestId, remains);
        return JSONObject.toJSONString(info);
    }

}
