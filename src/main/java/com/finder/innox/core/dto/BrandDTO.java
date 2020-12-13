package com.finder.innox.core.dto;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import com.finder.innox.core.domain.Brand;
import com.finder.innox.utils.CommonStatus;

public class BrandDTO implements Serializable {

	private static final long serialVersionUID = 4225662124317516233L;

	private long seq;

	private String name;

	private MultipartFile imageFile;

	private String imagePath;

	private Integer sequence;

	private Integer status;

	private String statusDesc;

	public BrandDTO() {
		super();
	}

	public BrandDTO(Brand brand) {
		if (brand != null) {
			this.seq = brand.getSeq();
			this.name = brand.getName();
			this.sequence = brand.getSequence();
			this.imagePath = brand.getImagePath();
			this.status = brand.getStatus() == null ? CommonStatus.INACTIVE.getCode() : brand.getStatus();
			this.statusDesc = CommonStatus.getDescByCode(this.status);
			System.out.println("############ " + this.statusDesc);
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

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public MultipartFile getImageFile() {
		return imageFile;
	}

	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

}
