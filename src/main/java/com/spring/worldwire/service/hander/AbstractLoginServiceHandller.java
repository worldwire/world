package com.spring.worldwire.service.hander;

import com.spring.worldwire.model.LoginInfo;
import com.spring.worldwire.model.UserAccount;
import com.spring.worldwire.model.UserInfo;
import com.spring.worldwire.query.LoginInfoQuery;
import com.spring.worldwire.service.LoginInfoService;
import com.spring.worldwire.service.LoginService;
import com.spring.worldwire.service.UserAccountService;
import com.spring.worldwire.service.UserInfoService;
import com.spring.worldwire.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author luxun.
 * @date 2018/5/15 10:54
 */
public abstract class AbstractLoginServiceHandller implements LoginService {

    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private LoginInfoService loginInfoService;
    @Autowired
    private UserInfoService userInfoService;

    public void handleHttpParams(HttpServletRequest request, HttpServletResponse response, Long id) {
        Cookie cookie = new Cookie("loginKey" + id, id.toString());
        response.addCookie(cookie);
    }

    public void handleSignUp(Long id) throws ParseException {
        UserAccount account = userAccountService.selectByUserId(id);
        if (Objects.isNull(account)) {
            return;
        }
        if (DateUtil.dateInterval(account.getLastSignTime(), new Date()) > 1) {
            account.setSignNum(0);//上次签到时间比当前时间大于一天
            userAccountService.updateUserAccount(account);
        }
    }

    /**
     * 登录处理，分为普通登录和第三方登录
     *
     * @param loginInfo
     */
    public LoginInfo handleLoginCheck(LoginInfo loginInfo,HttpServletRequest request,HttpServletResponse response) {

        List<LoginInfo> list = new ArrayList<LoginInfo>();

        if (Objects.nonNull(loginInfo.getEmail()) && Objects.nonNull(loginInfo.getPassword())) {

            list = loginInfoService.selectByQuery(buildQuery(loginInfo));

            return list.stream().findFirst().orElse(null);
        }
        if (Objects.nonNull(loginInfo.getThirdType()) && Objects.nonNull(loginInfo.getThirdKey())) {

            LoginInfoQuery query = new LoginInfoQuery();
            query.setThirdType(loginInfo.getThirdType());
            query.setThirdKey(loginInfo.getThirdKey());

            list = loginInfoService.selectByQuery(query);
        }
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        LoginInfo info = list.get(0);
        UserInfo userInfo = userInfoService.selectByLoginId(info.getId());

        return list.stream().findFirst().orElse(null);

    }


    protected LoginInfoQuery buildQuery(LoginInfo loginInfo) {
        LoginInfoQuery query = new LoginInfoQuery();
        query.setEmail(loginInfo.getEmail());
        query.setPassword(loginInfo.getPassword());
        return query;
    }

    @Override
    public String auth() {
        return null;
    }

    @Override
    public LoginInfo callback(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

}
