package com.finder.ecoshop.response;

import java.io.Serializable;

import com.finder.ecoshop.core.dto.ColorDTO;

public class ColorResponse implements Serializable {

	private static final long serialVersionUID = -209023380741455623L;

	private long color_id;

	private String color_name;

	private String color_code;

	public ColorResponse() {
		super();
	}

	public ColorResponse(ColorDTO color) {
		super();
		this.color_id = color.getSeq();
		this.color_code = color.getColorCode();
		this.color_name = color.getColorName();
	}

	public long getColor_id() {
		return color_id;
	}

	public void setColor_id(long color_id) {
		this.color_id = color_id;
	}

	public String getColor_name() {
		return color_name;
	}

	public void setColor_name(String color_name) {
		this.color_name = color_name;
	}

	public String getColor_code() {
		return color_code;
	}

	public void setColor_code(String color_code) {
		this.color_code = color_code;
	}

}
