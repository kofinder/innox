package com.finder.ecoshop.core.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.web.multipart.MultipartFile;

import com.finder.ecoshop.core.domain.CustomItemLayout;
import com.finder.ecoshop.utils.CommonStatus;

public class CustomItemLayoutDTO implements Serializable {

	private static final long serialVersionUID = 3447791253429069883L;

	private long seq;

	private CustomItemDTO customItemDTO;

	private String layoutName;

	private BigDecimal layoutPrice;

	private String layoutImage;

	private MultipartFile layoutImageFile;

	private UserDTO userDTO;

	private long customProductId;

	private long customItemId;
	
	private int status;

	public CustomItemLayoutDTO() {
		super();
	}

	public CustomItemLayoutDTO(CustomItemLayout itemLayout) {
		if (itemLayout != null) {
			this.seq = itemLayout.getSeq();
			this.customItemDTO = new CustomItemDTO(itemLayout.getCustomItem());
			this.layoutName = itemLayout.getLayoutName();
			this.layoutPrice = itemLayout.getLayoutPrice() == null ? BigDecimal.ZERO : itemLayout.getLayoutPrice();
			this.layoutImage = itemLayout.getLayoutImage();
			this.status = itemLayout.getStatue() == null ? CommonStatus.ACTIVE.getCode() : itemLayout.getStatue();
		}
	}

	public long getSeq() {
		return seq;
	}

	public void setSeq(long seq) {
		this.seq = seq;
	}

	public CustomItemDTO getCustomItemDTO() {
		return customItemDTO;
	}

	public void setCustomItemDTO(CustomItemDTO customItemDTO) {
		this.customItemDTO = customItemDTO;
	}

	public String getLayoutName() {
		return layoutName;
	}

	public void setLayoutName(String layoutName) {
		this.layoutName = layoutName;
	}

	public BigDecimal getLayoutPrice() {
		return layoutPrice;
	}

	public void setLayoutPrice(BigDecimal layoutPrice) {
		this.layoutPrice = layoutPrice;
	}

	public String getLayoutImage() {
		return layoutImage;
	}

	public void setLayoutImage(String layoutImage) {
		this.layoutImage = layoutImage;
	}

	public MultipartFile getLayoutImageFile() {
		return layoutImageFile;
	}

	public void setLayoutImageFile(MultipartFile layoutImageFile) {
		this.layoutImageFile = layoutImageFile;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public long getCustomProductId() {
		return customProductId;
	}

	public void setCustomProductId(long customProductId) {
		this.customProductId = customProductId;
	}

	public long getCustomItemId() {
		return customItemId;
	}

	public void setCustomItemId(long customItemId) {
		this.customItemId = customItemId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
