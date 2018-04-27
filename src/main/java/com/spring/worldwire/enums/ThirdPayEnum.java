package com.spring.worldwire.enums;

public enum ThirdPayEnum {

	PAY_PAL(1,"paypal支付")

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


}
