package com.finder.innox.utils;

public enum ProductTypeEnum {

	INSTOCK(1, "Instock"), CUSTOM(2, "Custom");

	private int code;

	private String desc;

	private ProductTypeEnum(int code, String desc) {
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
		for (ProductTypeEnum type : ProductTypeEnum.values()) {
			if (code == type.code) {
				return type.desc;
			}
		}

		return "";
	}
}
