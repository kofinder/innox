package com.finder.innox.core.dto;

import java.io.Serializable;

import com.finder.innox.core.domain.Color;
import com.finder.innox.utils.CommonStatus;

public class ColorDTO implements Serializable {

	private static final long serialVersionUID = -2663412535124162132L;

	private long seq;

	private String colorCode;

	private String colorName;

	private int status;

	private String statusDesc;

	public ColorDTO() {
		super();
	}

	public ColorDTO(Color color) {
		if (color != null) {
			this.seq = color.getSeq();
			this.colorCode = color.getColorCode();
			this.colorName = color.getColorName();
			this.status = color.getStatus() == null ? CommonStatus.ACTIVE.getCode() : color.getStatus();
			this.statusDesc = CommonStatus.getDescByCode(this.status);
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

}
