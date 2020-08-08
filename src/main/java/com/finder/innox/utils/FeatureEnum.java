package com.finder.innox.utils;

public enum FeatureEnum {
	FEATURE(1), NOT_FEATURE(2);

	private int code;

	private FeatureEnum(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
