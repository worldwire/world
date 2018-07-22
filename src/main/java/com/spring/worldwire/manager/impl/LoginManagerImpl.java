package com.spring.worldwire.manager.impl;

import com.spring.worldwire.constants.Constants;
import com.spring.worldwire.manager.LoginManager;
import com.spring.worldwire.model.LoginInfo;
import com.spring.worldwire.model.UserAccount;
import com.spring.worldwire.model.UserInfo;
import com.spring.worldwire.query.LoginInfoQuery;
import com.spring.worldwire.service.LoginInfoService;
import com.spring.worldwire.service.UserAccountService;
import com.spring.worldwire.service.UserInfoService;
import com.spring.worldwire.utils.Base64;
import com.spring.worldwire.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Desc: Desc
 * User: luxun
 * Date: 2018/7/16 17:07
 * To change this template use File | Settings | File and Code Templates | Includes | File Header.
 */
@Service
public class LoginManagerImpl extends CookieManager implements LoginManager {

    private static Logger logger = LoggerFactory.getLogger(LoginManagerImpl.class);

    @Autowired
    private LoginInfoService loginInfoService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private UserAccountService userAccountService;

    @Override
    public UserInfo login(String email, String password, HttpServletResponse response) {

        LoginInfoQuery query = new LoginInfoQuery();
        query.setEmail(email);
        query.setPassword(password);
        List<LoginInfo> list = loginInfoService.selectByQuery(query);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        UserInfo userInfo = userInfoService.selectByLoginId(list.get(0).getId());
        if (Objects.isNull(userInfo)) {
            return null;
        }
        // 登录保存cookie
        processCookie(response, userInfo);
        // 处理签到
        handleSignUp(userInfo.getId());
        return userInfo;
    }

    @Override
    public UserInfo thirdLogin(String thirdKey, Integer thirdType, HttpServletResponse response) {
        LoginInfoQuery query = new LoginInfoQuery();
        query.setThirdKey(thirdKey);
        query.setThirdType(thirdType);
        List<LoginInfo> list = loginInfoService.selectByQuery(query);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        UserInfo userInfo = userInfoService.selectByLoginId(list.get(0).getId());
        if (Objects.isNull(userInfo)) {
            return null;
        }
        // 登录保存cookie
        processCookie(response, userInfo);
        // 处理签到
        handleSignUp(userInfo.getId());
        return userInfo;
    }

    @Override
    public void logout(HttpServletResponse response) {

        Cookie userIdCookie = new Cookie(Constants.USER_COOKIES_NAME, null);
        userIdCookie.setPath("/");
        userIdCookie.setMaxAge(0);
        response.addCookie(userIdCookie);
    }


    /**
     * 处理登录的连续签到
     *
     * @param userId
     */
    private void handleSignUp(Long userId) {
        UserAccount account = userAccountService.selectByUserId(userId);
        if (Objects.isNull(account)) {
            return;
        }
        try {
            if (DateUtil.dateInterval(account.getLastSignTime(), new Date()) > 1) {
                account.setSignNum(0);//上次签到时间比当前时间大于一天
                userAccountService.updateUserAccount(account);
            }
        } catch (ParseException e) {
            String message = String.format("time parse error, lastSignTime:%s, now:%s ", account.getLastSignTime(), new Date());
            logger.error(message, e);
            throw new RuntimeException(message);
        }
    }
}
