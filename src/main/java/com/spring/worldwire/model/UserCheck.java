package com.spring.worldwire.model;

import java.io.Serializable;
import java.util.Date;

public class UserCheck implements Serializable{

    private static final long serialVersionUID = -8411299760741575222L;

    private Long id;

    private Long reqId;

    private Long userId;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReqId() {
        return reqId;
    }

    public void setReqId(Long reqId) {
        this.reqId = reqId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserCheck{" +
                "id=" + id +
                ", reqId=" + reqId +
                ", userId=" + userId +
                ", createTime=" + createTime +
                '}';
    }
}