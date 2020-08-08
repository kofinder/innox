package com.finder.innox.core.dto;

import java.io.Serializable;

import com.finder.innox.core.domain.ProductColor;

public class ProductColorDTO implements Serializable {

	private static final long serialVersionUID = -307171834427209784L;

	private long seq;

	private ProductDTO productDTO;

	private ColorDTO colorDTO;

	public ProductColorDTO() {
		super();
	}

	public ProductColorDTO(ProductColor prdColor) {
		if (prdColor != null) {
			this.seq = prdColor.getSeq();
			this.productDTO = new ProductDTO(prdColor.getProduct());
			this.colorDTO = new ColorDTO(prdColor.getColor());
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

	public ColorDTO getColorDTO() {
		return colorDTO;
	}

	public void setColorDTO(ColorDTO colorDTO) {
		this.colorDTO = colorDTO;
	}

}
