package com.finder.innox.core.dto;

import java.io.Serializable;

import com.finder.innox.core.domain.Size;
import com.finder.innox.utils.CommonStatus;
import com.finder.innox.utils.ItemSizeCategoryEnum;

public class SizeDTO implements Serializable {

	private static final long serialVersionUID = 1322337819247408003L;

	private long seq;

	private String sizeCode;

	private String sizeName;

	private int sizeCategory;

	private String sizeCategoryDesc;

	private int status;

	private String statusDesc;

	public SizeDTO() {
		super();
	}

	public SizeDTO(Size size) {
		if (size != null) {
			this.seq = size.getSeq();
			this.sizeName = size.getSizeName();
			this.sizeCode = size.getSizeCode();
			this.status = size.getStatus() == null ? CommonStatus.ACTIVE.getCode() : size.getStatus();
			this.statusDesc = CommonStatus.getDescByCode(this.status);
			this.sizeCategory = size.getSizeCategory() == null ? ItemSizeCategoryEnum.SHIRT.getCode()
					: size.getSizeCategory();
			this.sizeCategoryDesc = ItemSizeCategoryEnum.getDescByCode(this.sizeCategory);
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

	public int getSizeCategory() {
		return sizeCategory;
	}

	public void setSizeCategory(int sizeCategory) {
		this.sizeCategory = sizeCategory;
	}

	public String getSizeCategoryDesc() {
		return sizeCategoryDesc;
	}

	public void setSizeCategoryDesc(String sizeCategoryDesc) {
		this.sizeCategoryDesc = sizeCategoryDesc;
	}

}
