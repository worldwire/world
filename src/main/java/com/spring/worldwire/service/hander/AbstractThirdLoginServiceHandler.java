package com.spring.worldwire.service.hander;

import com.spring.worldwire.model.LoginInfo;
import com.spring.worldwire.service.LoginService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Desc: Desc
 * User: luxun
 * Date: 2018/5/21 17:28
 * To change this template use File | Settings | File and Code Templates | Includes | File Header.
 */
public abstract class AbstractThirdLoginServiceHandler extends AbstractLoginServiceHandller{

    @Override
    public int register(LoginInfo info) {
        return 0;
    }

}
