package com.spring.worldwire.enums;

import com.spring.worldwire.extent.INumericEnum;

/**
 * @Auther pg
 * @Date create in 22:28 2018/7/16
 */
@SuppressWarnings("unused")
public enum  ProductTypeEnum implements INumericEnum {

    CHECK_MESSAGE(1,"查看次数"),
    TRANSLATE_CHINA_TO_ENGLISH(2,"翻译成英文"),
    TRANSLATE_ENGLISH_TO_CHINA(3,"翻译成中文"),
            ;

    private int code;

    private String demo;

    ProductTypeEnum(int code, String demo) {
        this.code = code;
        this.demo = demo;
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

    @Override
    public int getNumericValue() {
        return code;
    }
}
