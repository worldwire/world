package com.spring.worldwire.manager.impl;

import com.spring.worldwire.manager.RegisterManager;
import com.spring.worldwire.model.LoginInfo;
import com.spring.worldwire.model.UserAccount;
import com.spring.worldwire.model.UserInfo;
import com.spring.worldwire.service.LoginInfoService;
import com.spring.worldwire.service.UserAccountService;
import com.spring.worldwire.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RegisterManagerImpl implements RegisterManager {

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private LoginInfoService loginInfoService;


    @Override
    public void register(String userName, String email, String password) {

        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setUserName(userName);
        loginInfo.setEmail(email);
        loginInfo.setPassword(password);
        loginInfo.setCreateTime(new Date());
        loginInfo.setStatus((byte) 1);
        loginInfoService.insert(loginInfo);

        UserInfo userInfo = new UserInfo();
        userInfo.setLoginId(loginInfo.getId());
        userInfoService.insert(userInfo);

        UserAccount userAccount = new UserAccount();
        userAccount.setSignNum(0);
        userAccount.setViewingTimes(0);
        userAccount.setFreeTranslate(0);
        userAccount.setCreateTime(new Date());
        userAccount.setUserId(userInfo.getId());
        userAccountService.insert(userAccount);


    }

}