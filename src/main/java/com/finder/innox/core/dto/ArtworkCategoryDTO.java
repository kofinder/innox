package com.finder.innox.core.dto;

import java.io.Serializable;

import com.finder.innox.core.domain.ArtworkCategory;
import com.finder.innox.utils.CommonStatus;

public class ArtworkCategoryDTO implements Serializable {

	private static final long serialVersionUID = 9105512803125901444L;

	private long seq;

	private String categoryName;

	private String cateogryCode;

	private int status;

	public ArtworkCategoryDTO() {
		super();
	}

	public ArtworkCategoryDTO(ArtworkCategory artworkCategory) {
		if (artworkCategory != null) {
			this.seq = artworkCategory.getSeq();
			this.categoryName = artworkCategory.getCategoryName();
			this.cateogryCode = artworkCategory.getCateogryCode();
			this.status = artworkCategory.getStatus() == null ? CommonStatus.ACTIVE.getCode()
					: artworkCategory.getStatus();
		}
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCateogryCode() {
		return cateogryCode;
	}

	public void setCateogryCode(String cateogryCode) {
		this.cateogryCode = cateogryCode;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getSeq() {
		return seq;
	}

	public void setSeq(long seq) {
		this.seq = seq;
	}

}
