package com.finder.innox.core.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "product_font")
public class ProductFont extends CommonEntity {

	private static final long serialVersionUID = -7164613068245996769L;

	@ManyToOne
	@JoinColumn(name = "product_layout_id")
	private ProductLayout productLayout;

	@ManyToOne
	@JoinColumn(name = "fonts_id")
	private Fonts fonts;

	@Column(name = "price")
	private BigDecimal price;

	public ProductLayout getProductLayout() {
		return productLayout;
	}

	public void setProductLayout(ProductLayout productLayout) {
		this.productLayout = productLayout;
	}

	public Fonts getFonts() {
		return fonts;
	}

	public void setFonts(Fonts fonts) {
		this.fonts = fonts;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
