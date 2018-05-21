package com.spring.worldwire.service.hander;

import com.spring.worldwire.model.LoginInfo;
import com.spring.worldwire.model.UserAccount;
import com.spring.worldwire.query.LoginInfoQuery;
import com.spring.worldwire.service.LoginInfoService;
import com.spring.worldwire.service.LoginService;
import com.spring.worldwire.service.UserAccountService;
import com.spring.worldwire.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
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

    public void handleCookie(HttpServletResponse response, Long id) {
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
    public LoginInfo handleLoginCheck(LoginInfo loginInfo) {

        if (Objects.nonNull(loginInfo.getEmail()) && Objects.nonNull(loginInfo.getPassword())) {


            List<LoginInfo> list = loginInfoService.selectByQuery(buildQuery(loginInfo));

            return list.stream().findFirst().orElse(null);
        }
        if (Objects.nonNull(loginInfo.getThirdType()) && Objects.nonNull(loginInfo.getThirdKey())) {

            LoginInfoQuery query = new LoginInfoQuery();
            query.setThirdType(loginInfo.getThirdType());
            query.setThirdKey(loginInfo.getThirdKey());

            List<LoginInfo> list = loginInfoService.selectByQuery(query);

            return list.stream().findFirst().orElse(null);

        }
        return null;
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
