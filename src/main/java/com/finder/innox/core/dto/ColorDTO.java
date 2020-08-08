package com.finder.innox.core.dto;

import java.io.Serializable;

import com.finder.innox.core.domain.Color;

public class ColorDTO implements Serializable {

	private static final long serialVersionUID = -2663412535124162132L;

	private long seq;

	private String colorCode;

	private String colorName;

	public ColorDTO() {
		super();
	}

	public ColorDTO(Color color) {
		if (color != null) {
			this.seq = color.getSeq();
			this.colorCode = color.getColorCode();
			this.colorName = color.getColorName();
		}
	}

	public long getSeq() {
		return seq;
	}

	public void setSeq(long seq) {
		this.seq = seq;
	}

	public String getColorCode() {
		return colorCode;
	}

	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

}
