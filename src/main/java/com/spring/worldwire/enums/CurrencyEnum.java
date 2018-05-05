package com.spring.worldwire.enums;

import com.spring.worldwire.extent.INumericEnum;

@SuppressWarnings("unused")
public enum CurrencyEnum implements INumericEnum {

	RMB(1,"人民币","RMB"),
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

	public static CurrencyEnum getCurrencyByCode(int code){
		CurrencyEnum[] values = CurrencyEnum.values();
		for (CurrencyEnum value : values) {
			if(value.code == code)
				return value;
		}
		return null;
	}

	@Override
	public int getNumericValue() {
		return this.code;
	}
}
