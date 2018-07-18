package com.spring.worldwire.service.hander;

import com.spring.worldwire.model.LoginInfo;
import com.spring.worldwire.model.UserInfo;
import com.spring.worldwire.query.LoginInfoQuery;
import com.spring.worldwire.service.LoginInfoService;
import com.spring.worldwire.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Desc: Desc
 * User: luxun
 * Date: 2018/5/21 17:28
 * To change this template use File | Settings | File and Code Templates | Includes | File Header.
 */
public abstract class AbstractThirdLoginServiceHandler implements LoginService {

    @Autowired
    private LoginInfoService loginInfoService;

    @Override
    public int register(LoginInfo info) {
        return 0;
    }

    @Override
    public UserInfo login(LoginInfo info, HttpServletResponse response, HttpServletRequest request) {
        return null;
    }

    /**
     * 登录处理
     *
     * @param query
     */
    public LoginInfo handleLoginCheck(LoginInfoQuery query) {

        List<LoginInfo> list = loginInfoService.selectByQuery(query);
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        return list.stream().findFirst().orElse(null);

    }

}
