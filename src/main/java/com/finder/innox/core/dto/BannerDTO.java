package com.finder.innox.core.dto;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import com.finder.innox.core.domain.Banner;
import com.finder.innox.utils.CommonStatus;

public class BannerDTO implements Serializable {

	private static final long serialVersionUID = -7621568829898420709L;

	private long seq;

	private String name;

	private String description;

	private MultipartFile imageFile;

	private String imagePath;

	private int sequenceNo;

	private int status;

	private String createdTime;

	private UserDTO userDTO;
	
	private String statusDesc;

	public BannerDTO() {
		super();
	}

	public BannerDTO(Banner banner) {
		if (banner != null) {
			this.seq = banner.getSeq();
			this.name = banner.getName();
			this.description = banner.getDescription();
			this.imagePath = banner.getImagePath();
			this.sequenceNo = banner.getSequenceNo();
			this.status = banner.getStatus() == null ? 0 : banner.getStatus();
			this.userDTO = new UserDTO(banner.getCreatedBy());
			this.statusDesc = CommonStatus.getDescByCode(this.status);
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	
	

}
