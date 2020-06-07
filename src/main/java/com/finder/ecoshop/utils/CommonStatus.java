package com.finder.ecoshop.utils;

public enum CommonStatus {

	ACTIVE(1, "Active"), INACTIVE(2, "Inactive");

	private int code;

	private String desc;

	private CommonStatus(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
	
	public static String getDescByCode(int code) {
		for(CommonStatus status : values()) {
			if(status.getCode() == code) {
				return status.getDesc();
			}
		}
		
		return "";
	}

}
