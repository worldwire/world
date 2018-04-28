package com.spring.worldwire.enums;

import com.spring.worldwire.extent.INumericEnum;

public enum CurrencyEnum implements INumericEnum {

	RMD(1,"人民币","RMB"),
	USD(2,"美元","USD"),
	EUR(3,"欧元","EUR"),

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

	public String getAb() {
		return ab;
	}

	public void setAb(String ab) {
		this.ab = ab;
	}

	@Override
	public int getNumericValue() {
		return this.code;
	}
}
