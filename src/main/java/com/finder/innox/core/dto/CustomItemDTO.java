package com.finder.innox.core.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.finder.innox.core.domain.CustomItem;
import com.finder.innox.core.domain.CustomItemSize;

public class CustomItemDTO implements Serializable {

	private static final long serialVersionUID = -922920810492540974L;

	private long seq;

	private CustomProductDTO customProductDTO;

	private ColorDTO colorDTO;

	private String itemCode;

	private String itemName;

	private BigDecimal itemPrice;

	private UserDTO userDTO;

	private int sequenceNo;

	private List<Long> cusItemSizeList = new ArrayList<Long>();

	private List<CustomItemSizeDTO> customItemSizeList = new ArrayList<CustomItemSizeDTO>();

	private int sizeCategoryId;

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
			this.sequenceNo = customItem.getSequenceNo() == null ? 0 : customItem.getSequenceNo();

			for (CustomItemSize size : customItem.getCustomItemSizeList()) {
				if (size != null && size.getSeq() != null && size.getSize() != null
						&& size.getSize().getSeq() != null) {
					this.cusItemSizeList.add(size.getSize().getSeq());
					CustomItemSizeDTO customItemSizeDTO = new CustomItemSizeDTO();
					customItemSizeDTO.setSizeDTO(new SizeDTO(size.getSize()));
					this.customItemSizeList.add(customItemSizeDTO);
					;
				}
			}
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

	public int getSequenceNo() {
		return sequenceNo;
	}

	public void setSequenceNo(int sequenceNo) {
		this.sequenceNo = sequenceNo;
	}

	public List<Long> getCusItemSizeList() {
		return cusItemSizeList;
	}

	public void setCusItemSizeList(List<Long> cusItemSizeList) {
		this.cusItemSizeList = cusItemSizeList;
	}

	public int getSizeCategoryId() {
		return sizeCategoryId;
	}

	public void setSizeCategoryId(int sizeCategoryId) {
		this.sizeCategoryId = sizeCategoryId;
	}

	public List<CustomItemSizeDTO> getCustomItemSizeList() {
		return customItemSizeList;
	}

	public void setCustomItemSizeList(List<CustomItemSizeDTO> customItemSizeList) {
		this.customItemSizeList = customItemSizeList;
	}

}
