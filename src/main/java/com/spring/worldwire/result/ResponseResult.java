package com.spring.worldwire.result;

import com.spring.worldwire.enums.StatusCodeEnum;

/**
 * Desc: 返回结果封装类
 * User: luxun
 * Date: 2018/5/30 13:32
 * To change this template use File | Settings | File and Code Templates | Includes | File Header.
 */
public class ResponseResult<T> {

    T data;

    StatusCodeEnum code;

    String message;

    String desc;


    public ResponseResult(T data, StatusCodeEnum code, String desc) {
        this.data = data;
        this.desc = desc;
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public StatusCodeEnum getCode() {
        return code;
    }

    public void setCode(StatusCodeEnum code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("{");
        sb.append("data:").append(data).append(",");
        sb.append("desc:").append(desc).append(",");
        sb.append("message:").append(code.getMsg()).append(",");
        sb.append("code:").append(code.getCode());
        sb.append("}");
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new ResponseResult(null, StatusCodeEnum.EMPTY, "测试一下"));
    }
}
