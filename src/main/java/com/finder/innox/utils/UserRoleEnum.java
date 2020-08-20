package com.finder.innox.utils;

public enum UserRoleEnum {

	ROLE_USER(1, "ROLE_USER"), ROLE_ADMIN(2, "ROLE_ADMIN"), ROLE_DESIGNER(3, "ROLE_DESIGNER");

	private int code;

	private String desc;

	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	private UserRoleEnum(int code, String desc) {
		this.code = code;
	}

}
