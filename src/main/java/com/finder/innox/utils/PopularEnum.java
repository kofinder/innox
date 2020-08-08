package com.finder.innox.utils;

public enum PopularEnum {

	POPULAR(1), NOT_POPULAR(0);

	private int code;

	private PopularEnum(int code) {
		this.code = code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

}
