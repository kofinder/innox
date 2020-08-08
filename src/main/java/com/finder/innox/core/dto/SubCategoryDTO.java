package com.finder.innox.core.dto;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import com.finder.innox.core.domain.SubCategory;
import com.finder.innox.utils.CommonStatus;

public class SubCategoryDTO implements Serializable {

	private static final long serialVersionUID = -6892143845351703476L;

	private long seq;

	private String name;

	private MultipartFile imageFile;

	private String imagePath;

	private int sequenceNo;

	private int status;

	private CategoryDTO categoryDTO;

	private long categorySeq;

	public SubCategoryDTO() {
		super();
	}

	public SubCategoryDTO(SubCategory sub) {
		if (sub != null) {
			this.seq = sub.getSeq();
			this.name = sub.getName();
			this.imagePath = sub.getImagePath();
			this.sequenceNo = sub.getSequence() == null ? 0 : sub.getSequence();
			this.status = sub.getStatus() == null ? CommonStatus.INACTIVE.getCode() : sub.getStatus();
			this.categoryDTO = new CategoryDTO(sub.getCategory());
		}
	}

	public long getSeq() {
		return seq;
	}

	public void setSeq(long seq) {
		this.seq = seq;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MultipartFile getImageFile() {
		return imageFile;
	}

	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public int getSequenceNo() {
		return sequenceNo;
	}

	public void setSequenceNo(int sequenceNo) {
		this.sequenceNo = sequenceNo;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public CategoryDTO getCategoryDTO() {
		return categoryDTO;
	}

	public void setCategoryDTO(CategoryDTO categoryDTO) {
		this.categoryDTO = categoryDTO;
	}

	public long getCategorySeq() {
		return categorySeq;
	}

	public void setCategorySeq(long categorySeq) {
		this.categorySeq = categorySeq;
	}

}
