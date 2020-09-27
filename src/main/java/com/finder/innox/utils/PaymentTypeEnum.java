package com.finder.innox.utils;

public enum PaymentTypeEnum {

	OFFLINE(1, "Offline"), ONLINE(2, "Online");

	private PaymentTypeEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	private int code;

	private String desc;

	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	public static String getDescByCode(int code) {
		for (PaymentTypeEnum payment : PaymentTypeEnum.values()) {
			if (code == payment.getCode()) {
				return payment.getDesc();
			}
		}

		return "";
	}

}
