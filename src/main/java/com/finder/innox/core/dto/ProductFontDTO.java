package com.finder.innox.core.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.finder.innox.core.domain.ProductFont;

public class ProductFontDTO implements Serializable {

	private static final long serialVersionUID = 8818089379364034391L;

	private long seq;

	private ProductLayoutDTO productLayoutDTO;

	private FontsDTO fontsDTO;

	private BigDecimal price;

	public ProductFontDTO() {
		super();
	}

	public ProductFontDTO(ProductFont productFont) {
		if (productFont != null) {
			this.seq = productFont.getSeq();
			this.productLayoutDTO = new ProductLayoutDTO(productFont.getProductLayout());
			this.fontsDTO = new FontsDTO(productFont.getFonts());
			this.price = productFont.getPrice() == null ? BigDecimal.ZERO : productFont.getPrice();
		}
	}

	public long getSeq() {
		return seq;
	}

	public void setSeq(long seq) {
		this.seq = seq;
	}

	public ProductLayoutDTO getProductLayoutDTO() {
		return productLayoutDTO;
	}

	public void setProductLayoutDTO(ProductLayoutDTO productLayoutDTO) {
		this.productLayoutDTO = productLayoutDTO;
	}

	public FontsDTO getFontsDTO() {
		return fontsDTO;
	}

	public void setFontsDTO(FontsDTO fontsDTO) {
		this.fontsDTO = fontsDTO;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
