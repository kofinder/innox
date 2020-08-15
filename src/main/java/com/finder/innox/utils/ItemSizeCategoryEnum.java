package com.finder.innox.utils;

public enum ItemSizeCategoryEnum {

	SHIRT(1, "Shirt"), SHOE(2, "Shoe");

	private int code;

	private String desc;

	private ItemSizeCategoryEnum(int code, String desc) {
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
		for (ItemSizeCategoryEnum cat : values()) {
			if (cat.getCode() == code) {
				return cat.getDesc();
			}
		}

		return "";
	}

}
