package com.spring.worldwire.query;

import com.spring.worldwire.query.base.Pager;

/**
 * Desc: Desc
 * User: luxun
 * Date: 2018/7/9 15:37
 * To change this template use File | Settings | File and Code Templates | Includes | File Header.
 */
public class ProductRequestQuery extends Pager {
    // 用户类型 个人，企业
    private Integer userType;
    // 需求类型，帮助别人或者寻求帮助
    private Integer requestType;

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getRequestType() {
        return requestType;
    }

    public void setRequestType(Integer requestType) {
        this.requestType = requestType;
    }
}
