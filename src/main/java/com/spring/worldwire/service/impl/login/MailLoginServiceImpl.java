package com.spring.worldwire.service.impl.login;

import com.spring.worldwire.model.LoginInfo;
import com.spring.worldwire.service.LoginInfoService;
import com.spring.worldwire.service.hander.AbstractLoginServiceHandller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.List;
import java.util.Objects;

/**
 * Desc: Desc
 * User: luxun
 * Date: 2018/5/21 19:55
 * To change this template use File | Settings | File and Code Templates | Includes | File Header.
 */
@Service("mailService")
public class MailLoginServiceImpl extends AbstractLoginServiceHandller {

    @Autowired
    private LoginInfoService loginInfoService;

    @Override
    public int register(LoginInfo info) {
        return loginInfoService.registerByMail(info);
    }

    @Override
    public LoginInfo login(LoginInfo info, HttpServletResponse response, HttpServletRequest request) {

        List<LoginInfo> list = loginInfoService.selectByQuery(buildQuery(info));

        LoginInfo loginInfo = list.stream().findFirst().orElse(null);
        if (Objects.nonNull(loginInfo)) {
            try {
                handleHttpParams(request,response, list.get(0).getId());
                handleSignUp(list.get(0).getId());
                return loginInfo;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
