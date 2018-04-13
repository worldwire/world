package com.spring.worldwire.model;

import java.util.Date;

public class UserAccount {
    private Long id;

    private Integer viewingTimes;

    private Integer freeTranslate;

    private Long userId;

    private Integer signNum;

    private Date lastSignTime;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getViewingTimes() {
        return viewingTimes;
    }

    public void setViewingTimes(Integer viewingTimes) {
        this.viewingTimes = viewingTimes;
    }

    public Integer getFreeTranslate() {
        return freeTranslate;
    }

    public void setFreeTranslate(Integer freeTranslate) {
        this.freeTranslate = freeTranslate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getSignNum() {
        return signNum;
    }

    public void setSignNum(Integer signNum) {
        this.signNum = signNum;
    }

    public Date getLastSignTime() {
        return lastSignTime;
    }

    public void setLastSignTime(Date lastSignTime) {
        this.lastSignTime = lastSignTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}