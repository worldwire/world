package com.spring.worldwire.manager.impl;

import com.spring.worldwire.constants.Constants;
import com.spring.worldwire.enums.UserTypeEnum;
import com.spring.worldwire.manager.RegisterUserInfoManager;
import com.spring.worldwire.model.UserInfo;
import com.spring.worldwire.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther pg
 * @Date create in 19:56 2018/7/22
 */
@Service
public class RegisterUserInfoManagerImpl extends CookieManager implements RegisterUserInfoManager {


    @Autowired
    private UserInfoService userInfoService;

    public RegisterUserInfoManagerImpl(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @Override
    public void registerUserType(HttpServletRequest request, HttpServletResponse response, UserTypeEnum userType) {
        String userIdStr = request.getAttribute(Constants.USER_ID_SESSION).toString();
        UserInfo userInfo = new UserInfo();
        userInfo.setId(Long.parseLong(userIdStr));
        userInfo.setType(userType);
        userInfoService.update(userInfo);

        userInfoService.selectSimpleById(userInfo.getId());

        processCookie(response,userInfo);
    }

    @Override
    public void registerUserForeign(long userId, int type) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(userId);
        userInfo.setIsForeign(type);
        userInfoService.update(userInfo);
    }
}
