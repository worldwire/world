package com.spring.worldwire.service.impl.login;

import com.spring.worldwire.enums.ThirdLoginTypeEnum;
import com.spring.worldwire.model.LoginInfo;
import com.spring.worldwire.model.UserAccount;
import com.spring.worldwire.model.UserInfo;
import com.spring.worldwire.service.LoginInfoService;
import com.spring.worldwire.service.UserAccountService;
import com.spring.worldwire.service.UserInfoService;
import com.spring.worldwire.service.hander.AbstractThirdLoginServiceHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weibo4j.Oauth;
import weibo4j.Users;
import weibo4j.model.User;
import weibo4j.model.WeiboException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Objects;

/**
 * Desc: Desc
 * User: luxun
 * Date: 2018/5/21 18:29
 * To change this template use File | Settings | File and Code Templates | Includes | File Header.
 */
@Service("weiboService")
public class WeiBoServiceImpl extends AbstractThirdLoginServiceHandler {

    @Autowired
    private LoginInfoService loginInfoService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private UserAccountService userAccountService;

    private static Logger log = LoggerFactory.getLogger(WeiBoServiceImpl.class);

    @Override
    public String auth() {
        Oauth oauth = new Oauth();
        try {
            return oauth.authorize("code", null);
        } catch (WeiboException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public LoginInfo callback(HttpServletRequest request, HttpServletResponse response) {
        String code = request.getParameter("code");
        Oauth oauth = new Oauth();
        String token = "";
        try {
            token = oauth.getAccessTokenByCode(code).toString();
            String str[] = token.split(",");
            String accessToken = str[0].split("=")[1];
            String str1[] = str[3].split("]");
            String uid = str1[0].split("=")[1];
            Users users = new Users();
            users.client.setToken(accessToken);
            User weiboUser = users.showUserById(uid);
            LoginInfo info = handleLoginCheck(warpLoginUser(weiboUser),request,response);
            LoginInfo user = new LoginInfo();
            if (Objects.isNull(info)) {
                user = createNewUser(weiboUser);
            } else {
                user = info;
            }
            //登录操作
            handleHttpParams(request,response, user.getId());
            handleSignUp(user.getId());
        } catch (Exception e) {
            log.error("callback exception", e);
        }
        return null;
    }

    private LoginInfo createNewUser(User weiboUser) {
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setCreateTime(new Date());
        loginInfo.setStatus((byte) 1);
        loginInfo.setThirdType(ThirdLoginTypeEnum.WEIBO.getCode());
        loginInfo.setThirdKey(weiboUser.getId());
        int status = loginInfoService.insert(loginInfo);
        if (status == 1) {//创建成功，则添加用户信息
            UserInfo userInfo = new UserInfo();
            userInfo.setLoginId(loginInfo.getId());
            userInfo.setNickName(weiboUser.getName());
            userInfo.setShowImg(weiboUser.getProfileImageUrl());
            userInfoService.insert(userInfo);

            UserAccount account = new UserAccount();
            account.setUserId(loginInfo.getId());
            account.setFreeTranslate(0);
            account.setSignNum(0);
            account.setViewingTimes(0);
            account.setCreateTime(new Date());
            userAccountService.insert(account);
        }
        return loginInfo;
    }

    private LoginInfo warpLoginUser(User weiboUser) {
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setThirdType(ThirdLoginTypeEnum.WEIBO.getCode());
        loginInfo.setThirdKey(weiboUser.getId());
        return loginInfo;
    }
}
