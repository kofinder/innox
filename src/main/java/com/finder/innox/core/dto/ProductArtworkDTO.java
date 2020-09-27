package com.finder.innox.core.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.finder.innox.core.domain.ProductArtwork;

public class ProductArtworkDTO implements Serializable {

	private static final long serialVersionUID = 6051418013499978584L;

	private long seq;

	private ProductLayoutDTO productLayoutDTO;

	private ArtworkDTO artworkDTO;

	private BigDecimal price;

	public ProductArtworkDTO() {
		super();
	}

	public ProductArtworkDTO(ProductArtwork productArtwork) {
		if (productArtwork != null) {
			this.seq = productArtwork.getSeq();
			this.productLayoutDTO = new ProductLayoutDTO(productArtwork.getProductLayout());
			this.artworkDTO = new ArtworkDTO(productArtwork.getArtwork());
			this.price = productArtwork.getPrice() == null ? BigDecimal.ZERO : productArtwork.getPrice();
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

	public ArtworkDTO getArtworkDTO() {
		return artworkDTO;
	}

	public void setArtworkDTO(ArtworkDTO artworkDTO) {
		this.artworkDTO = artworkDTO;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
