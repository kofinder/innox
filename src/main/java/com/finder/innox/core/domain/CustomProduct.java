package com.finder.innox.core.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * 
 * @author Zay Maung Maung Myint
 *
 */
@Entity
@Table(name = "custom_product")
public class CustomProduct extends CommonEntity implements Serializable {

	private static final long serialVersionUID = -1176056139594306059L;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	@ManyToOne
	@JoinColumn(name = "sub_category_id")
	private SubCategory subCategory;

	@Column(name = "product_name")
	private String prodcutName;

	@Column(name = "product_price")
	private BigDecimal initialPrice;

	@Column(name = "default_image")
	private String imagePath;

	@Column(name = "created_by_id")
	private User user;

	@Column(name = "statue")
	private Integer status;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public SubCategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}

	public String getProdcutName() {
		return prodcutName;
	}

	public void setProdcutName(String prodcutName) {
		this.prodcutName = prodcutName;
	}

	public BigDecimal getInitialPrice() {
		return initialPrice;
	}

	public void setInitialPrice(BigDecimal initialPrice) {
		this.initialPrice = initialPrice;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
