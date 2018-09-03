package com.spring.worldwire.enums;

import com.alibaba.fastjson.JSONObject;
import com.spring.worldwire.extent.INumericEnum;

@SuppressWarnings("unused")
public enum RequestTypeEnum implements INumericEnum {

    SERVICE(1, "服务"),
    REQUEST(2, "需求"),;

    private int code;

    private String demo;

    RequestTypeEnum(int code, String enName) {
        this.code = code;
        this.demo = enName;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDemo() {
        return demo;
    }

    public void setDemo(String demo) {
        this.demo = demo;
    }

    public static RequestTypeEnum getNameByCode(int code) {
        RequestTypeEnum[] array = RequestTypeEnum.values();
        for (RequestTypeEnum value : array) {
            if (code == value.getCode()) {
                return value;
            }
        }
        return null;
    }

    @Override
    public int getNumericValue() {
        return this.code;
    }

}
