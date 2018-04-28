package com.spring.worldwire.enums;

import com.spring.worldwire.extent.INumericEnum;

public enum ThirdPayEnum implements INumericEnum {

	PAY_PAL(1,"paypal支付"),
	ALIPAY(2,"支付宝"),
	;

	private int code;

	private String name;

	ThirdPayEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int getNumericValue() {
		return this.code;
	}
}
