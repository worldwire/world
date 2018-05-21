package com.spring.worldwire.enums;

/**
 * Desc: Desc
 * User: luxun
 * Date: 2018/5/21 16:59
 * To change this template use File | Settings | File and Code Templates | Includes | File Header.
 */
public enum ThirdLoginTypeEnum {

    LINKEDIN(1,"领英"),
    QQ(2,"qq"),
    WHCHAT(3,"微信"),
    WEIBO(4,"微博");
    private int code;

    private String msg;

    ThirdLoginTypeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
