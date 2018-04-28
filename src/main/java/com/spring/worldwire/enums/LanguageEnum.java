package com.spring.worldwire.enums;

public enum LanguageEnum {
	
	CHINESE(1,"chinese","中文"),
	ENGLISH(2,"english","英语"),
	JAPANESE(3,"japanese","日语"),
	FRENSH(4,"french","法语"),
	GERMAN(5,"german","德语"),
	SPANISH(6,"spanish","西班牙语");
	
	private int code;
	
	private String enName;
	
	private String cnName;

	private LanguageEnum(int code, String enName, String cnName) {
		this.code = code;
		this.enName = enName;
		this.cnName = cnName;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getCnName() {
		return cnName;
	}

	public void setCnName(String cnName) {
		this.cnName = cnName;
	}
	
	public static String getNameByCode(int code){
		LanguageEnum[] array = LanguageEnum.values();
		for (LanguageEnum languageEnum : array) {
			if(code == languageEnum.getCode()){
				return languageEnum.getEnName();
			}
		}
		return "";
	}

}
