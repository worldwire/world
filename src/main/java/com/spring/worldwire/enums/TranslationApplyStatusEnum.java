package com.spring.worldwire.enums;

import com.spring.worldwire.extent.INumericEnum;

@SuppressWarnings("unused")
public enum TranslationApplyStatusEnum implements INumericEnum {
	INIT(0,"初始状态"),
	NORMAL(1,"支付可翻译"),
	AUDITION(2,"翻译待审核"),
	SUCCESS(3,"审核通过"),
	FAIL(4,"审核不通过");


	TranslationApplyStatusEnum(int code, String name) {
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

	public static TranslationApplyStatusEnum getNameByCode(int code){
		TranslationApplyStatusEnum[] array = TranslationApplyStatusEnum.values();
		for (TranslationApplyStatusEnum value : array) {
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
