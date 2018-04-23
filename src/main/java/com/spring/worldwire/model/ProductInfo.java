package com.spring.worldwire.model;

public class ProductInfo {
    private Long id;

    private String productName;

    private Integer amount;

    private Integer payType;

    private Integer type;

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
        this.productName = productName == null ? null : productName.trim();
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

	@Override
	public String toString() {
		return "ProductInfo [id=" + id + ", productName=" + productName + ", amount=" + amount + ", payType=" + payType
				+ ", type=" + type + "]";
	}
    
}