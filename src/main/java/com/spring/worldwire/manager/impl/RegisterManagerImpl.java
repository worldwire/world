package com.spring.worldwire.manager.impl;

import com.spring.worldwire.constants.Constants;
import com.spring.worldwire.manager.RegisterManager;
import com.spring.worldwire.model.LoginInfo;
import com.spring.worldwire.model.UserAccount;
import com.spring.worldwire.model.UserInfo;
import com.spring.worldwire.query.LoginInfoQuery;
import com.spring.worldwire.service.LoginInfoService;
import com.spring.worldwire.service.UserAccountService;
import com.spring.worldwire.service.UserInfoService;
import com.spring.worldwire.utils.MailUtils;
import com.spring.worldwire.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class RegisterManagerImpl implements RegisterManager {

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private LoginInfoService loginInfoService;
    @Autowired
    private RedisUtils redisUtils;


    @Override
    @Transactional
    public LoginInfo register(String userName, String email, String password) {

        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setUserName(userName);
        loginInfo.setNickName(userName);
        loginInfo.setEmail(email);
        loginInfo.setPassword(password);
        loginInfo.setCreateTime(new Date());
        loginInfo.setStatus((byte) 0);
        loginInfoService.insert(loginInfo);

        UserInfo userInfo = new UserInfo();
        userInfo.setNickName(userName);
        userInfo.setLoginId(loginInfo.getId());
        userInfo.setShowImg("/images/defaultHeadImg.png");
        userInfo.setExhibition("");
        userInfoService.insert(userInfo);

        UserAccount userAccount = new UserAccount();
        userAccount.setSignNum(0);
        userAccount.setViewingTimes(0);
        userAccount.setFreeTranslate(0);
        userAccount.setCreateTime(new Date());
        userAccount.setUserId(userInfo.getId());
        userAccountService.insert(userAccount);

        sendSimpleMail(loginInfo);
        return loginInfo;

    }

    private void sendSimpleMail(LoginInfo loginInfo) {


        Date date = new Date();
        String url = Constants.MAIL_ACTIVE_USER_PREFIX + "/" + loginInfo.getId() + "/" + date.getTime();
        try {
            MailUtils.sendActiveMail(loginInfo.getEmail(), date, url);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("mailSendError");
        }
//        redisUtils.set(CACHE_MAIL_VALID_PREFIX + loginInfo.getId() + "_" + date.getTime() ,"" ,MAIL_CODE_INVALIDATE_TIME);

    }

    @Override
    public LoginInfo selectByUserName(String userName) {
        LoginInfoQuery query = new LoginInfoQuery();
        query.setUserName(userName);
        return loginInfoService.selectByQuery(query).stream().findFirst().orElse(null);
    }

    @Override
    public LoginInfo selectByEmail(String email) {
        LoginInfoQuery query = new LoginInfoQuery();
        query.setEmail(email);
        return loginInfoService.selectByQuery(query).stream().findFirst().orElse(null);
    }
}
