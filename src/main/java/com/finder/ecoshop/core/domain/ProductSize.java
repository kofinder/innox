package com.finder.ecoshop.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "product_size")
public class ProductSize extends CommonEntity implements Serializable {

	private static final long serialVersionUID = -7000006822616877960L;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	@Column(name = "size_name")
	private String sizeName;

	@ManyToOne
	@JoinColumn(name = "created_by_id")
	private AdminUser createdBy;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getSizeName() {
		return sizeName;
	}

	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}

	public AdminUser getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(AdminUser createdBy) {
		this.createdBy = createdBy;
	}

}
