package com.spring.worldwire.enums;

/**
 * Created by luxun on 2018/4/28.
 */
public enum StatusCodeEnum {

    SUCCCESS(1,"success","成功"),
    FAIL(2,"fail","失败"),
    EMPTY(3,"empty result","没有数据"),
    EXISTS(4,"exists already","已经存在"),
    OUT_OF_DATE(5,"out of date","已过期"),
    ERROR(-1,"error","出错");

    StatusCodeEnum(Integer code, String msg, String desc) {
        this.code = code;
        this.msg = msg;
        this.desc = desc;
    }

    private Integer code;

    private String msg;

    private String desc;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
