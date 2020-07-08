package com.finder.ecoshop.core.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.finder.ecoshop.core.domain.CustomItem;

public class CustomItemDTO implements Serializable {

	private static final long serialVersionUID = -922920810492540974L;

	private long seq;

	private CustomProductDTO customProductDTO;

	private ColorDTO colorDTO;

	private String itemCode;

	private String itemName;

	private BigDecimal itemPrice;

	private UserDTO userDTO;

	public CustomItemDTO() {
		super();
	}

	public CustomItemDTO(CustomItem customItem) {
		if (customItem != null) {
			this.seq = customItem.getSeq();
			this.customProductDTO = new CustomProductDTO(customItem.getCustomProduct());
			this.colorDTO = new ColorDTO(customItem.getColor());
			this.itemCode = customItem.getItemCode();
			this.itemName = customItem.getItemName();
			this.itemPrice = customItem.getItemPrice() == null ? BigDecimal.ZERO : customItem.getItemPrice();
		}
	}

	public long getSeq() {
		return seq;
	}

	public void setSeq(long seq) {
		this.seq = seq;
	}

	public CustomProductDTO getCustomProductDTO() {
		return customProductDTO;
	}

	public void setCustomProductDTO(CustomProductDTO customProductDTO) {
		this.customProductDTO = customProductDTO;
	}

	public ColorDTO getColorDTO() {
		return colorDTO;
	}

	public void setColorDTO(ColorDTO colorDTO) {
		this.colorDTO = colorDTO;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public BigDecimal getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(BigDecimal itemPrice) {
		this.itemPrice = itemPrice;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

}
