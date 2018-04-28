package com.spring.worldwire.enums;

import com.spring.worldwire.extent.INumericEnum;

public enum PayStatusEnum implements INumericEnum {

	HAVING(0,"进行中"),
	SUCCESS(1,"成功"),
	FAIL(3,"失败"),

	;

	private int code;

	private String name;

	PayStatusEnum(int code, String name) {
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
