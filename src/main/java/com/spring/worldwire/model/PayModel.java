package com.spring.worldwire.model;

import com.spring.worldwire.enums.CurrencyEnum;
import com.spring.worldwire.enums.ThirdPayEnum;
import java.math.BigDecimal;

public class PayModel {
  private String orderNum;
  private Integer user_id;
  private BigDecimal amount;
  private String detail;
  private ThirdPayEnum thirdPayEnum;
  private CurrencyEnum currencyEnum;

  public PayModel(String orderNum, Integer user_id, BigDecimal amount, String detail,ThirdPayEnum thirdPayEnum,CurrencyEnum currencyEnum) {
    this.orderNum = orderNum;
    this.user_id = user_id;
    this.amount = amount;
    this.detail = detail;
    this.thirdPayEnum = thirdPayEnum;
    this.currencyEnum = currencyEnum;
  }

  public String getOrderNum() {
    return orderNum;
  }

  public void setOrderNum(String orderNum) {
    this.orderNum = orderNum;
  }

  public Integer getUser_id() {
    return user_id;
  }

  public void setUser_id(Integer user_id) {
    this.user_id = user_id;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public String getDetail() {
    return detail;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }

  public ThirdPayEnum getThirdPayEnum() {
    return thirdPayEnum;
  }

  public void setThirdPayEnum(ThirdPayEnum thirdPayEnum) {
    this.thirdPayEnum = thirdPayEnum;
  }
}
