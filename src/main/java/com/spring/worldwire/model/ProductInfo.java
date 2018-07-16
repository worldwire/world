package com.spring.worldwire.model;

import com.spring.worldwire.enums.CurrencyEnum;
import com.spring.worldwire.enums.ProductTypeEnum;

import java.io.Serializable;
import java.math.BigDecimal;

@SuppressWarnings("unused")
public class ProductInfo implements Serializable{

    private static final long serialVersionUID = 3453425989173545796L;

    private Long id;

    private String productName;

    private BigDecimal amount;

    private CurrencyEnum payType;

    private ProductTypeEnum type;

    private Integer times;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public CurrencyEnum getPayType() {
        return payType;
    }

    public void setPayType(CurrencyEnum payType) {
        this.payType = payType;
    }

    public ProductTypeEnum getType() {
        return type;
    }

    public void setType(ProductTypeEnum type) {
        this.type = type;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }
}