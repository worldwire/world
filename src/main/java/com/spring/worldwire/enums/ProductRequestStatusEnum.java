package com.spring.worldwire.enums;

import com.spring.worldwire.extent.INumericEnum;

@SuppressWarnings("unused")
public enum ProductRequestStatusEnum implements INumericEnum {
	INIT(0,"初始状态"),
	NORMAL(1,"正常状态"),
	ON(2,"上架"),
	OFF(3,"下架");


	ProductRequestStatusEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	private int code;

	private String name;

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

	public static ProductRequestStatusEnum getNameByCode(int code){
		ProductRequestStatusEnum[] array = ProductRequestStatusEnum.values();
		for (ProductRequestStatusEnum value : array) {
			if(code == value.getCode()){
				return value;
			}
		}
		return null;
	}

	@Override
	public int getNumericValue() {
		return this.code;
	}
}
