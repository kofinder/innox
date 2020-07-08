package com.finder.ecoshop.core.dto;

import java.io.Serializable;

import com.finder.ecoshop.core.domain.CustomItemSize;

public class CustomItemSizeDTO implements Serializable {

	private static final long serialVersionUID = 5805486415879552305L;

	private long seq;

	private CustomItemDTO customItemDTO;

	private SizeDTO sizeDTO;

	private UserDTO userDTO;

	public CustomItemSizeDTO() {
		super();
	}

	public CustomItemSizeDTO(CustomItemSize customSize) {
		if (customSize != null) {
			this.seq = customSize.getSeq();
			this.customItemDTO = new CustomItemDTO(customSize.getCustomItem());
			this.sizeDTO = new SizeDTO(customSize.getSize());
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

	public SizeDTO getSizeDTO() {
		return sizeDTO;
	}

	public void setSizeDTO(SizeDTO sizeDTO) {
		this.sizeDTO = sizeDTO;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

}
