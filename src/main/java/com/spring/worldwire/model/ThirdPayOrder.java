package com.spring.worldwire.model;

import com.spring.worldwire.enums.CurrencyEnum;
import com.spring.worldwire.enums.PayStatusEnum;
import com.spring.worldwire.enums.ProductTypeEnum;
import com.spring.worldwire.enums.ThirdPayEnum;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther pg
 * @Date create in 21:39 2018/7/18
 */
@SuppressWarnings("unused")
public class ThirdPayOrder implements Serializable {

    private Long id;
    private Long userId;
    private String paymentNo;
    private BigDecimal amount;
    private Long productId;
    private ProductTypeEnum productType;
    private ThirdPayEnum thirdType;
    private CurrencyEnum currency;
    private PayStatusEnum payStatus;
    private Long foreignId;
    private Date createTime;
    private Date updateTime;
    private String payDetail;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPaymentNo() {
        return paymentNo;
    }

    public void setPaymentNo(String paymentNo) {
        this.paymentNo = paymentNo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public ProductTypeEnum getProductType() {
        return productType;
    }

    public void setProductType(ProductTypeEnum productType) {
        this.productType = productType;
    }

    public ThirdPayEnum getThirdType() {
        return thirdType;
    }

    public void setThirdType(ThirdPayEnum thirdType) {
        this.thirdType = thirdType;
    }

    public CurrencyEnum getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyEnum currency) {
        this.currency = currency;
    }

    public PayStatusEnum getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(PayStatusEnum payStatus) {
        this.payStatus = payStatus;
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

    public Long getForeignId() {
        return foreignId;
    }

    public void setForeignId(Long foreignId) {
        this.foreignId = foreignId;
    }

    public String getPayDetail() {
        return payDetail;
    }

    public void setPayDetail(String payDetail) {
        this.payDetail = payDetail;
    }
}
