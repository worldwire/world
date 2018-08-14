package com.spring.worldwire.query;

import com.spring.worldwire.query.base.Pager;

/**
 * Created by luxun on 2018/4/21.
 */
public class LoginInfoQuery extends Pager {

    private String userName;

    private String password;

    private String email;

    private Integer thirdType;

    private String thirdKey;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Integer getThirdType() {
        return thirdType;
    }

    public void setThirdType(Integer thirdType) {
        this.thirdType = thirdType;
    }

    public String getThirdKey() {
        return thirdKey;
    }

    public void setThirdKey(String thirdKey) {
        this.thirdKey = thirdKey;
    }
}
