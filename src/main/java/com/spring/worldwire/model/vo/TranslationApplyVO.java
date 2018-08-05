package com.spring.worldwire.model.vo;

import java.util.Date;

@SuppressWarnings("unused")
public class TranslationApplyVO {
    private Long id;

    private Long operatorId;

    private String operatorName;

    private Date operatorTime;

    private Long auditorId;

    private String auditorName;

    private Date auditorTime;

    private String auditorMsg;

    private String translationContext;

    private String translationTitle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
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

    public String getAuditorName() {
        return auditorName;
    }

    public void setAuditorName(String auditorName) {
        this.auditorName = auditorName;
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
        this.auditorMsg = auditorMsg;
    }

    public String getTranslationContext() {
        return translationContext;
    }

    public void setTranslationContext(String translationContext) {
        this.translationContext = translationContext;
    }

    public String getTranslationTitle() {
        return translationTitle;
    }

    public void setTranslationTitle(String translationTitle) {
        this.translationTitle = translationTitle;
    }
}