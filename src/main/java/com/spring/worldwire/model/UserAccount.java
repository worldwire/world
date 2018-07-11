package com.spring.worldwire.model;

import java.io.Serializable;
import java.util.Date;

public class UserAccount implements Serializable{

    private static final long serialVersionUID = -94931448867844759L;

    private Long id;

    private Integer viewingTimes;

    private Integer freeTranslate;

    private Long userId;

    private Integer signNum;

    private Date lastSignTime;

    private Date createTime;

    private Date updateTime;

    private Integer version;

    private Integer oldVersion;

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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getOldVersion() {
        return oldVersion;
    }

    public void setOldVersion(Integer oldVersion) {
        this.oldVersion = oldVersion;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("UserAccount{");
        sb.append("id=").append(id);
        sb.append(", viewingTimes=").append(viewingTimes);
        sb.append(", freeTranslate=").append(freeTranslate);
        sb.append(", userId=").append(userId);
        sb.append(", signNum=").append(signNum);
        sb.append(", lastSignTime=").append(lastSignTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", version=").append(version);
        sb.append('}');
        return sb.toString();
    }
}