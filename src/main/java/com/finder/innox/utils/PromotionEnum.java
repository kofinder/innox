package com.finder.innox.utils;

public enum PromotionEnum {

	PROMOTION(1), NOT_PROMOTION(0);

	private int code;

	private PromotionEnum(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

}
