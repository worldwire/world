package com.spring.worldwire.service.impl.login;

import com.alibaba.fastjson.JSONObject;
import com.spring.worldwire.enums.ThirdLoginTypeEnum;
import com.spring.worldwire.model.LoginInfo;
import com.spring.worldwire.model.UserAccount;
import com.spring.worldwire.model.UserInfo;
import com.spring.worldwire.service.LoginInfoService;
import com.spring.worldwire.service.UserAccountService;
import com.spring.worldwire.service.UserInfoService;
import com.spring.worldwire.service.hander.AbstractThirdLoginServiceHandler;
import com.spring.worldwire.utils.HttpUtils;
import com.spring.worldwire.config.login.LinkedInConfig;
import com.spring.worldwire.config.login.LinkedInHelper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.Date;
import java.util.Objects;

/**
 * Desc: Desc
 * User: luxun
 * Date: 2018/5/21 17:35
 * To change this template use File | Settings | File and Code Templates | Includes | File Header.
 */
@Service("linkedInService")
public class LinkedInServiceImpl extends AbstractThirdLoginServiceHandler {

    @Autowired
    private LinkedInHelper linkedInHelper;
    @Autowired
    private LinkedInConfig linkedInConfig;
    @Autowired
    private LoginInfoService loginInfoService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private UserAccountService userAccountService;

    private static Logger log = LoggerFactory.getLogger(LinkedInServiceImpl.class);

    @Override
    public String auth() {
        return linkedInHelper.authorize();
    }

    @Override
    public LoginInfo callback(HttpServletRequest request, HttpServletResponse response) {

        String code = request.getParameter("code");
        String state = request.getParameter("state");

        String tempState = linkedInConfig.getState();
        // 防止跨站攻击
        if (null == tempState || null == state || !tempState.trim().equalsIgnoreCase(state.trim())) {
            String ip = HttpUtils.getIpAddress(request);
            log.error("state not equal origin state {},get state {},request ip={},check the ip to ensure safety", tempState, state, ip);
            return null;
        }
        // 判断第一步是否得到Code
        if (StringUtils.isEmpty(code)) {
            // 登录失败处理
            log.error("login callback fail ,get empty code info");
            return null;
        } else {
            JSONObject tokenInfo = linkedInHelper.getAccessToken(code);
            if (null == tokenInfo || !tokenInfo.containsKey("access_token")) {
                // 获取Token失败处理
                log.error("get invalide accessToken info  --> {}", tokenInfo);
                return null;
            } else {
                // 用Token获取用户信息
                String accessToken = tokenInfo.getString("access_token");
                if (StringUtils.isEmpty(accessToken)) {
                    // 获取Token失败处理
                    return null;
                }
                JSONObject userInfo = linkedInHelper.getUserInfo(accessToken);
                if (!userInfo.containsKey("id")) {
                    // 获取信息失败
                    return null;
                } else {
                    // 获取信息成功,保存用户
                    LoginInfo user = new LoginInfo();
                    LoginInfo info = handleLoginCheck(warpLoginUser(userInfo),request,response);
                    if (Objects.isNull(info)) {//用户不存在则创建一个新用户
                        user = createNewUser(userInfo);
                    }else{
                        user = info;
                    }
                    try {
                        //登录操作
                        handleHttpParams(request,response, user.getId());
                        handleSignUp(user.getId());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return user;
                }
            }
        }
    }

    private LoginInfo createNewUser(JSONObject info) {
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setCreateTime(new Date());
        loginInfo.setStatus((byte) 1);
        loginInfo.setThirdType(ThirdLoginTypeEnum.LINKEDIN.getCode());
        loginInfo.setThirdKey(info.getString("id"));
        int status = loginInfoService.insert(loginInfo);
        if (status == 1) {//创建成功，则添加用户信息
            UserInfo userInfo = new UserInfo();
            userInfo.setLoginId(loginInfo.getId());
            userInfo.setNickName(info.getString("lastName") + info.getString("firstName"));
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

    private LoginInfo warpLoginUser(JSONObject userInfo) {
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setThirdType(ThirdLoginTypeEnum.LINKEDIN.getCode());
        loginInfo.setThirdKey(userInfo.getString("id"));
//        loginInfo.setUserName(userInfo.getString("lastName") + userInfo.getString("firstName"));
        return loginInfo;
    }

}
