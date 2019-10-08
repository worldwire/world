package com.spring.worldwire.query;

import com.spring.worldwire.enums.RequestTypeEnum;
import com.spring.worldwire.enums.UserTypeEnum;
import com.spring.worldwire.query.base.Pager;

/**
 * Desc: Desc
 * User: luxun
 * Date: 2018/7/9 15:37
 * To change this template use File | Settings | File and Code Templates | Includes | File Header.
 */
public class ProductRequestQuery extends Pager {
    // 用户类型 个人，企业
    private UserTypeEnum userType;
    // 需求类型，帮助别人或者寻求帮助
    private RequestTypeEnum requestType;
    // userId
    private Long userId;

    private Integer nationType;

    private String key;

    public UserTypeEnum getUserType() {
        return userType;
    }

    public void setUserType(UserTypeEnum userType) {
        this.userType = userType;
    }

    public RequestTypeEnum getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestTypeEnum requestType) {
        this.requestType = requestType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getNationType() {
        return nationType;
    }

    public void setNationType(Integer nationType) {
        this.nationType = nationType;
    }
}
