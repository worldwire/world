package com.spring.worldwire.model;

import java.io.Serializable;

/**
 * @author luxun.
 * @date 2018/5/15 11:16
 */
public class ThirdLogin implements Serializable{

    private static final long serialVersionUID = 555480011824873303L;

    private String userName;

    private Integer thirdType;

    private String thirdKey;

    private String headImg;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }
}
