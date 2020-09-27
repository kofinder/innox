package com.finder.innox.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "product_layout")
public class ProductLayout extends CommonEntity {

	private static final long serialVersionUID = -7580678303890346565L;

	@ManyToOne()
	@JoinColumn(name = "product_id")
	private Product product;

	@ManyToOne
	@JoinColumn(name = "custom_item_layout_id")
	private CustomItemLayout customItemLayout;

	@Column(name = "created_design_image")
	private String createdDesignImage;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public CustomItemLayout getCustomItemLayout() {
		return customItemLayout;
	}

	public void setCustomItemLayout(CustomItemLayout customItemLayout) {
		this.customItemLayout = customItemLayout;
	}

	public String getCreatedDesignImage() {
		return createdDesignImage;
	}

	public void setCreatedDesignImage(String createdDesignImage) {
		this.createdDesignImage = createdDesignImage;
	}

}
