package com.finder.innox.core.dto;

import java.io.Serializable;

import com.finder.innox.core.domain.Size;

public class SizeDTO implements Serializable {

	private static final long serialVersionUID = 1322337819247408003L;

	private long seq;

	private String sizeCode;

	private String sizeName;

	public SizeDTO() {
		super();
	}

	public SizeDTO(Size size) {
		if (size != null) {
			this.seq = size.getSeq();
			this.sizeName = size.getSizeName();
			this.sizeCode = size.getSizeCode();
		}
	}

	public long getSeq() {
		return seq;
	}

	public void setSeq(long seq) {
		this.seq = seq;
	}

	public String getSizeCode() {
		return sizeCode;
	}

	public void setSizeCode(String sizeCode) {
		this.sizeCode = sizeCode;
	}

	public String getSizeName() {
		return sizeName;
	}

	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}

}
