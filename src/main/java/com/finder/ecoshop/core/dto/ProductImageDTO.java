package com.finder.ecoshop.core.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.finder.ecoshop.core.domain.ProductImage;
import com.finder.ecoshop.utils.CommonUtil;

public class ProductImageDTO implements Serializable {

	private static final long serialVersionUID = 1907424484038127932L;

	private long seq;

	private ProductDTO productDTO;

	private String description;

	private String color;

	private String size;

	private List<String> sizeList;

	private String imagePath;

	private MultipartFile imageFile;

	public ProductImageDTO() {
		super();
	}

	public ProductImageDTO(ProductImage image) {
		if (image != null) {
			this.seq = image.getSeq();
			this.productDTO = new ProductDTO(image.getProduct());
			this.description = image.getDescription();
			this.color = image.getColor();
			this.size = image.getSize();
			this.sizeList = !CommonUtil.isEmpty(image.getSize()) ? Arrays.asList(image.getSize().split("\\s*,\\s*"))
					: new ArrayList<String>();
			this.imagePath = image.getImagePath();
		}
	}

	public long getSeq() {
		return seq;
	}

	public void setSeq(long seq) {
		this.seq = seq;
	}

	public ProductDTO getProductDTO() {
		return productDTO;
	}

	public void setProductDTO(ProductDTO productDTO) {
		this.productDTO = productDTO;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
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

	public List<String> getSizeList() {
		return sizeList;
	}

	public void setSizeList(List<String> sizeList) {
		this.sizeList = sizeList;
	}

}
