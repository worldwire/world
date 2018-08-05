package com.spring.worldwire.model;

import com.spring.worldwire.enums.LanguageEnum;
import com.spring.worldwire.enums.TranslationApplyStatusEnum;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("unused")
public class TranslationApply implements Serializable{
    private static final long serialVersionUID = 6711257418819083454L;

    private Long id;

    private Long reqId;

    private LanguageEnum origType;

    private Long fromReqId;

    private LanguageEnum fromType;

    private TranslationApplyStatusEnum status;

    private String title;

    private Long operatorId;

    private String operatorName;

    private Date operatorTime;

    private Long auditorId;

    private String auditorName;

    private Date auditorTime;

    private String auditorMsg;

    private Date createTime;

    private Date updateTime;

    private Long userId;

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

    public Long getFromReqId() {
        return fromReqId;
    }

    public void setFromReqId(Long fromReqId) {
        this.fromReqId = fromReqId;
    }

    public LanguageEnum getOrigType() {
        return origType;
    }

    public void setOrigType(LanguageEnum origType) {
        this.origType = origType;
    }

    public LanguageEnum getFromType() {
        return fromType;
    }

    public void setFromType(LanguageEnum fromType) {
        this.fromType = fromType;
    }

    public TranslationApplyStatusEnum getStatus() {
        return status;
    }

    public void setStatus(TranslationApplyStatusEnum status) {
        this.status = status;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public Date getOperatorTime() {
        return operatorTime;
    }

    public void setOperatorTime(Date operatorTime) {
        this.operatorTime = operatorTime;
    }

    public Long getAuditorId() {
        return auditorId;
    }

    public void setAuditorId(Long auditorId) {
        this.auditorId = auditorId;
    }

    public Date getAuditorTime() {
        return auditorTime;
    }

    public void setAuditorTime(Date auditorTime) {
        this.auditorTime = auditorTime;
    }

    public String getAuditorMsg() {
        return auditorMsg;
    }

    public void setAuditorMsg(String auditorMsg) {
        this.auditorMsg = auditorMsg == null ? null : auditorMsg.trim();
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getAuditorName() {
        return auditorName;
    }

    public void setAuditorName(String auditorName) {
        this.auditorName = auditorName;
    }

    @Override
    public String toString() {
        return "TranslationApply{" +
                "id=" + id +
                ", reqId=" + reqId +
                ", origType=" + origType +
                ", fromReqId=" + fromReqId +
                ", fromType=" + fromType +
                ", status=" + status +
                ", operatorId=" + operatorId +
                ", operatorTime=" + operatorTime +
                ", auditorId=" + auditorId +
                ", auditorTime=" + auditorTime +
                ", auditorMsg='" + auditorMsg + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", userId=" + userId +
                '}';
    }
}