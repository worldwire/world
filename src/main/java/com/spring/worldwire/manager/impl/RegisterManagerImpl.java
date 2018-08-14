package com.spring.worldwire.manager.impl;

import com.spring.worldwire.constants.Constants;
import com.spring.worldwire.manager.RegisterManager;
import com.spring.worldwire.model.LoginInfo;
import com.spring.worldwire.model.UserAccount;
import com.spring.worldwire.model.UserInfo;
import com.spring.worldwire.query.LoginInfoQuery;
import com.spring.worldwire.service.LoginInfoService;
import com.spring.worldwire.service.MailService;
import com.spring.worldwire.service.UserAccountService;
import com.spring.worldwire.service.UserInfoService;
import com.spring.worldwire.utils.MailUtils;
import com.spring.worldwire.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Date;

import static com.spring.worldwire.constants.Constants.CACHE_MAIL_VALID_PREFIX;
import static com.spring.worldwire.constants.Constants.MAIL_CODE_INVALIDATE_TIME;

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
    public void register(String userName, String email, String password) {

        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setUserName(userName);
        loginInfo.setNickName(userName);
        loginInfo.setEmail(email);
        loginInfo.setPassword(password);
        loginInfo.setCreateTime(new Date());
        loginInfo.setStatus((byte) 0);
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

        sendSimpleMail(loginInfo);

    }

    private void sendSimpleMail(LoginInfo loginInfo) {


        Date date = new Date();
        String url = Constants.MAIL_ADDRESS_PREFIX + "/"+loginInfo.getId()+"/"+date.getTime();
        try {
            MailUtils.sendSimpleMail(loginInfo.getEmail(), date, url);
        } catch (Exception e) {
            throw new RuntimeException("mailSendError");
        }
        redisUtils.set(CACHE_MAIL_VALID_PREFIX + loginInfo.getId() + "_" + date.getTime() ,"" ,MAIL_CODE_INVALIDATE_TIME);

    }

    @Override
    public LoginInfo selectByUserName(String userName) {
        LoginInfoQuery query = new LoginInfoQuery();
        query.setUserName(userName);
        return loginInfoService.selectByQuery(query).stream().findFirst().orElse(null);
    }

}
