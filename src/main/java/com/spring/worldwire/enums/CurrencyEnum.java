package com.spring.worldwire.enums;

public enum CurrencyEnum {

	RMD(1,"人民币","rmb"),
	USD(2,"美元","usd"),
	EUR(3,"欧元","eur"),

	;

	private int code;

	private String name;
	private String ab;

	CurrencyEnum(int code, String name,String ab) {
		this.code = code;
		this.name = name;
		this.ab = ab;
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
