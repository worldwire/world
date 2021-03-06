package com.spring.worldwire.enums;

import com.spring.worldwire.extent.INumericEnum;

/**
 * Desc: Desc
 * User: luxun
 * Date: 2018/7/9 16:17
 * To change this template use File | Settings | File and Code Templates | Includes | File Header.
 */
public enum UserTypeEnum implements INumericEnum {
    PERSONAL(1, "个人"),
    ENTERPRISE(2, "企业");

    private int code;

    private String name;

    UserTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public static UserTypeEnum getNameByCode(int code) {
        if (code == 0) {
            return null;
        }
        UserTypeEnum[] array = UserTypeEnum.values();
        for (UserTypeEnum value : array) {
            if (code == value.getCode()) {
                return value;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "UserTypeEnum{" +
                "code=" + code +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int getNumericValue() {
        return code;
    }
}
