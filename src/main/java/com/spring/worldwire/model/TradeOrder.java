package com.spring.worldwire.model;

import com.spring.worldwire.config.BaseConfig;
import com.spring.worldwire.enums.CurrencyEnum;
import com.spring.worldwire.enums.PayStatusEnum;
import com.spring.worldwire.enums.ThirdPayEnum;
import java.math.BigDecimal;
import java.util.Date;

public class TradeOrder {

  private String orderNum;
  private Integer userId;
  private BigDecimal amount;
  private Integer total;
  private String tradeName;
  private String tradeDetail;
  private String thirdOrderNum;
  private ThirdPayEnum thirdType;
  private CurrencyEnum currency;
  private PayStatusEnum status;
  private Date createTime;
  private Date updateTime;
  private Date successTime;

  public TradeOrder(String orderNum, Integer userId, BigDecimal amount, String tradeDetail,ThirdPayEnum thirdType,CurrencyEnum currency) {
    this.orderNum = orderNum;
    this.userId = userId;
    this.amount = amount;
    this.tradeDetail = tradeDetail;
    this.thirdType = thirdType;
    this.currency = currency;
    init();
  }

  public void init(){
    this.status = PayStatusEnum.HAVING;
    this.createTime = new Date();
    this.updateTime = new Date();
    if(this.total==null){
      total = 1;
    }
    if(this.tradeName==null){
      tradeDetail = BaseConfig.DONAME;
    }
    if(tradeDetail==null){
      tradeDetail = BaseConfig.DOBODY;
    }
  }

  public String getOrderNum() {
    return orderNum;
  }

  public void setOrderNum(String orderNum) {
    this.orderNum = orderNum;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public String getTradeDetail() {
    return tradeDetail;
  }

  public void setTradeDetail(String tradeDetail) {
    this.tradeDetail = tradeDetail;
  }

  public ThirdPayEnum getThirdType() {
    return thirdType;
  }

  public void setThirdType(ThirdPayEnum thirdType) {
    this.thirdType = thirdType;
  }

  public Integer getTotal() {
    return total;
  }

  public void setTotal(Integer total) {
    this.total = total;
  }

  public String getTradeName() {
    return tradeName;
  }

  public void setTradeName(String tradeName) {
    this.tradeName = tradeName;
  }

  public String getThirdOrderNum() {
    return thirdOrderNum;
  }

  public void setThirdOrderNum(String thirdOrderNum) {
    this.thirdOrderNum = thirdOrderNum;
  }

  public CurrencyEnum getCurrency() {
    return currency;
  }

  public void setCurrency(CurrencyEnum currency) {
    this.currency = currency;
  }

  public PayStatusEnum getStatus() {
    return status;
  }

  public void setStatus(PayStatusEnum status) {
    this.status = status;
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

  public Date getSuccessTime() {
    return successTime;
  }

  public void setSuccessTime(Date successTime) {
    this.successTime = successTime;
  }
}
