package com.spring.worldwire.model.vo;

import com.spring.worldwire.enums.LanguageEnum;
import com.spring.worldwire.enums.TranslationApplyStatusEnum;

import java.util.Date;

@SuppressWarnings("unused")
public class TranslationApplyVO {
    private Long id;

    private Long reqId;

    private LanguageEnum origType;

    private Long fromReqId;

    private LanguageEnum fromType;

    private String title;

    private String context;

    private Long operatorId;

    private String translationContext;

    private String translationTitle;

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

    public LanguageEnum getOrigType() {
        return origType;
    }

    public void setOrigType(LanguageEnum origType) {
        this.origType = origType;
    }

    public Long getFromReqId() {
        return fromReqId;
    }

    public void setFromReqId(Long fromReqId) {
        this.fromReqId = fromReqId;
    }

    public LanguageEnum getFromType() {
        return fromType;
    }

    public void setFromType(LanguageEnum fromType) {
        this.fromType = fromType;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getTranslationContext() {
        return translationContext;
    }

    public void setTranslationContext(String translationContext) {
        this.translationContext = translationContext;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTranslationTitle() {
        return translationTitle;
    }

    public void setTranslationTitle(String translationTitle) {
        this.translationTitle = translationTitle;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }
}