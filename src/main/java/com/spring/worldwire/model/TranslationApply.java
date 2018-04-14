package com.spring.worldwire.model;

import java.util.Date;

public class TranslationApply {
    private Long id;

    private Long reqId;

    private Integer origType;

    private Long fromReqId;

    private Integer fromType;

    private Integer status;

    private Long operatorId;

    private Date operatorTime;

    private Long auditorId;

    private Date auditorTime;

    private String auditorMsg;

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

    public Integer getOrigType() {
        return origType;
    }

    public void setOrigType(Integer origType) {
        this.origType = origType;
    }

    public Long getFromReqId() {
        return fromReqId;
    }

    public void setFromReqId(Long fromReqId) {
        this.fromReqId = fromReqId;
    }

    public Integer getFromType() {
        return fromType;
    }

    public void setFromType(Integer fromType) {
        this.fromType = fromType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
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

	@Override
	public String toString() {
		return "TranslationApply [id=" + id + ", reqId=" + reqId + ", origType=" + origType + ", fromReqId=" + fromReqId
				+ ", fromType=" + fromType + ", status=" + status + ", operatorId=" + operatorId + ", operatorTime="
				+ operatorTime + ", auditorId=" + auditorId + ", auditorTime=" + auditorTime + ", auditorMsg="
				+ auditorMsg + "]";
	}
    
}