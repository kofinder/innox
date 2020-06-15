package com.finder.ecoshop.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class ProductImage extends CommonEntity implements Serializable {

	private static final long serialVersionUID = -3222065366653290927L;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	@Column(name = "description")
	private String description;

	@Column(name = "color")
	private String color;

	@Column(name = "size")
	private String size;

	@Column(name = "image_path")
	private String imagePath;

	@ManyToOne
	@JoinColumn(name = "created_by_id")
	private AdminUser createdBy;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
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

	public AdminUser getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(AdminUser createdBy) {
		this.createdBy = createdBy;
	}

}
