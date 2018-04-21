package com.spring.worldwire.query;

import com.spring.worldwire.query.base.Pager;

/**
 * Created by luxun on 2018/4/21.
 */
public class LoginInfoQuery extends Pager {

    private String password;

    private String email;

    public LoginInfoQuery() {
        super(10);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
