package com.finder.innox.core.dto;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import com.finder.innox.core.domain.Category;
import com.finder.innox.utils.CommonStatus;

public class CategoryDTO implements Serializable {

	private static final long serialVersionUID = 3400502893537355297L;

	private long seq;

	private String name;

	private String imagePath;

	private MultipartFile imageFile;

	private int sequenceNo;

	private int status;

	private int feature;

	public CategoryDTO() {
		super();
	}

	public CategoryDTO(Category c) {
		if (c != null) {
			this.seq = c.getSeq();
			this.name = c.getName();
			this.imagePath = c.getImagePath();
			this.sequenceNo = c.getSequence() == null ? 0 : c.getSequence();
			this.status = c.getStatus() == null ? CommonStatus.INACTIVE.getCode() : c.getStatus();
			this.feature = c.getFeature();
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

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public MultipartFile getImageFile() {
		return imageFile;
	}

	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
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

	public int getFeature() {
		return feature;
	}

	public void setFeature(int feature) {
		this.feature = feature;
	}

}
