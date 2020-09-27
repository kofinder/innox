package com.finder.innox.core.dto;

import java.io.Serializable;

import com.finder.innox.core.domain.ProductLayout;

public class ProductLayoutDTO implements Serializable {

	private static final long serialVersionUID = -6669542874586269924L;

	private long seq;

	private ProductDTO productDTO;

	private CustomItemLayoutDTO customItemLayoutDTO;

	private String createdDesignImage;

	public ProductLayoutDTO() {
		super();
	}

	public ProductLayoutDTO(ProductLayout productLayout) {
		if (productLayout != null) {
			this.seq = productLayout.getSeq();
			this.productDTO = new ProductDTO(productLayout.getProduct());
			this.customItemLayoutDTO = new CustomItemLayoutDTO(productLayout.getCustomItemLayout());
			this.createdDesignImage = productLayout.getCreatedDesignImage();
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

	public CustomItemLayoutDTO getCustomItemLayoutDTO() {
		return customItemLayoutDTO;
	}

	public void setCustomItemLayoutDTO(CustomItemLayoutDTO customItemLayoutDTO) {
		this.customItemLayoutDTO = customItemLayoutDTO;
	}

	public String getCreatedDesignImage() {
		return createdDesignImage;
	}

	public void setCreatedDesignImage(String createdDesignImage) {
		this.createdDesignImage = createdDesignImage;
	}

}
