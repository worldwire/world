package com.spring.worldwire.manager;

import com.spring.worldwire.model.LoginInfo;

public interface RegisterManager {

    void register(String userName,String email, String password);

    LoginInfo selectByUserName(String userName);
}
