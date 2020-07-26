package com.finder.ecoshop.core.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.web.multipart.MultipartFile;

import com.finder.ecoshop.core.domain.CustomProduct;
import com.finder.ecoshop.utils.CommonStatus;

public class CustomProductDTO implements Serializable {

	private static final long serialVersionUID = 7803007078314554811L;

	private long seq;

	private CategoryDTO categoryDTO;

	private SubCategoryDTO subCategoryDTO;

	private String productName;

	private BigDecimal initialPrice;

	private String imagePath;

	private MultipartFile imageFile;

	private UserDTO userDTO;

	private int status;

	public CustomProductDTO() {
		super();
	}

	public CustomProductDTO(CustomProduct customPrd) {
		if (customPrd != null) {
			this.seq = customPrd.getSeq();
			this.categoryDTO = new CategoryDTO(customPrd.getCategory());
			this.subCategoryDTO = new SubCategoryDTO(customPrd.getSubCategory());
			this.productName = customPrd.getProdcutName();
			this.initialPrice = customPrd.getInitialPrice() == null ? BigDecimal.ZERO : customPrd.getInitialPrice();
			this.imagePath = customPrd.getImagePath();
			this.status = customPrd.getStatus() == null ? CommonStatus.ACTIVE.getCode() : customPrd.getStatus();
		}
	}

	public long getSeq() {
		return seq;
	}

	public void setSeq(long seq) {
		this.seq = seq;
	}

	public CategoryDTO getCategoryDTO() {
		return categoryDTO;
	}

	public void setCategoryDTO(CategoryDTO categoryDTO) {
		this.categoryDTO = categoryDTO;
	}

	public SubCategoryDTO getSubCategoryDTO() {
		return subCategoryDTO;
	}

	public void setSubCategoryDTO(SubCategoryDTO subCategoryDTO) {
		this.subCategoryDTO = subCategoryDTO;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getInitialPrice() {
		return initialPrice;
	}

	public void setInitialPrice(BigDecimal initialPrice) {
		this.initialPrice = initialPrice;
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

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
