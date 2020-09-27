package com.finder.innox.utils;

public enum PaymentStatusEnum {

	PENDING(1, "Pending"), FINISH(2, "Finish"), CANCEL(3, "Cancel"), ERROR(4, "Error");

	Integer code;
	String description;

	@Override
	public String toString() {
		return description;
	}

	private PaymentStatusEnum(Integer code, String description) {
		this.code = code;
		this.description = description;
	}

	public Integer getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public static String getDescription(int code) {
		for (PaymentStatusEnum p : PaymentStatusEnum.values()) {
			if (p.code == code)
				return p.getDescription();
		}
		return "";
	}
}
