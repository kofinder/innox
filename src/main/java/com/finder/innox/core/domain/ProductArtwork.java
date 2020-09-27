package com.finder.innox.core.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "product_artwork")
public class ProductArtwork extends CommonEntity {

	private static final long serialVersionUID = -6636368784576427994L;

	@ManyToOne
	@JoinColumn(name = "product_layout_id")
	private ProductLayout productLayout;

	@ManyToOne
	@JoinColumn(name = "artwork_id")
	private Artwork artwork;

	private BigDecimal price;

	public ProductLayout getProductLayout() {
		return productLayout;
	}

	public void setProductLayout(ProductLayout productLayout) {
		this.productLayout = productLayout;
	}

	public Artwork getArtwork() {
		return artwork;
	}

	public void setArtwork(Artwork artwork) {
		this.artwork = artwork;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
