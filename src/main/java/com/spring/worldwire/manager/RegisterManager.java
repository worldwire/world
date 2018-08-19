package com.spring.worldwire.manager;

import com.spring.worldwire.model.LoginInfo;

public interface RegisterManager {

    LoginInfo register(String userName,String email, String password);

    LoginInfo selectByUserName(String userName);

    LoginInfo selectByEmail(String email);
}
