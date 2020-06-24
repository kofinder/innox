package com.finder.ecoshop.core.dto;

import java.io.Serializable;

import com.finder.ecoshop.core.domain.ProductSize;

public class ProductSizeDTO implements Serializable {

	private static final long serialVersionUID = -9146310658462489199L;

	private long seq;

	private ProductDTO productDTO;

	private String sizeName;

	public ProductSizeDTO() {
		super();
	}

	public ProductSizeDTO(ProductSize size) {
		if (size != null) {
			this.seq = size.getSeq();
			this.productDTO = new ProductDTO(size.getProduct());
			this.sizeName = size.getSizeName();
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

	public String getSizeName() {
		return sizeName;
	}

	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}

}
