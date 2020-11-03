package com.finder.innox.utils;

public enum DeviceTypeEnum {

	ANDROID(1, "Android"), IOS(2, "IOS");

	Integer code;
	String description;

	@Override
	public String toString() {
		return description;
	}

	private DeviceTypeEnum(Integer code, String description) {
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
		for (DeviceTypeEnum u : DeviceTypeEnum.values()) {
			if (u.code == code)
				return u.getDescription();
		}
		return "";
	}

	public static boolean isValidCode(int code) {
		for (DeviceTypeEnum t : DeviceTypeEnum.values()) {
			if (t.getCode() == code) {
				return true;
			}
		}
		return false;
	}

}
