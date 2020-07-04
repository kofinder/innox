package com.finder.ecoshop.core.dto;

import java.io.Serializable;

import com.finder.ecoshop.core.domain.ProductSize;

public class ProductSizeDTO implements Serializable {

	private static final long serialVersionUID = -9146310658462489199L;

	private long seq;

	private ProductDTO productDTO;

	private SizeDTO sizeDTO;

	public ProductSizeDTO() {
		super();
	}

	public ProductSizeDTO(ProductSize prdSize) {
		if (prdSize != null) {
			this.seq = prdSize.getSeq();
			this.productDTO = new ProductDTO(prdSize.getProduct());
			this.sizeDTO = new SizeDTO(prdSize.getSize());
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

	public SizeDTO getSizeDTO() {
		return sizeDTO;
	}

	public void setSizeDTO(SizeDTO sizeDTO) {
		this.sizeDTO = sizeDTO;
	}

}
