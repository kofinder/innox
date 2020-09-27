package com.finder.innox.utils;

public enum OrderStatusEnum {

	PROCESSING(1, "Processing"), DELIVERY_IN_PROGRESS(2, "Delivery in progress"), FINISH(3, "Finish"),
	CANCEL(4, "Cancel");

	private OrderStatusEnum(int code, String description) {
		this.code = code;
		this.description = description;
	}

	private int code;

	private String description;

	public static String getDescriptionByCode(int code) {
		for (OrderStatusEnum status : values()) {
			if (status.getCode() == code) {
				return status.getDescription();
			}
		}
		return "";
	}

	public int getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

}
