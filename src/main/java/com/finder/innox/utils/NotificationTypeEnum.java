package com.finder.innox.utils;

public enum NotificationTypeEnum {

	NOTI_MESSAGE(1, "Notification Message"), NEW_VERSION(2, "New Version"), ORDER_PURCHASE(3, "Order Purchase");

	private int code;

	private String description;

	private NotificationTypeEnum(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public static String getDescriptionByCode(int code) {
		for (NotificationTypeEnum status : values()) {
			if (status.getCode() == code) {
				return status.getDescription();
			}
		}
		return "";
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
