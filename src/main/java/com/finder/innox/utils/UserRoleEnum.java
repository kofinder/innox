package com.finder.innox.utils;

public enum UserRoleEnum {

	ROLE_ADMIN(1, "ROLE_ADMIN"), ROLE_USER(2, "ROLE_USER"), ROLE_DESIGNER(3, "ROLE_DESIGNER");

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
		this.desc = desc;
	}

	public static String getDescByCode(int code) {
		for (UserRoleEnum role : UserRoleEnum.values()) {
			if (code == role.getCode()) {
				return role.getDesc();
			}
		}
		return "";
	}

}
