package com.finder.ecoshop.core.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class C2BProduct extends CommonEntity implements Serializable {

	private static final long serialVersionUID = 8945554868547322762L;

	@Column(name = "Name")
	private String name;

	@Column(name = "Product_Code")
	private String productCode;

	@Column(name = "Price")
	private BigDecimal price;

	@Column(name = "Sequence")
	private Integer sequence;

	@Column(name = "Status")
	private Integer status;

	@ManyToOne
	@JoinColumn(name = "Category_Id")
	private Category category;

	@ManyToOne
	@JoinColumn(name = "SubCategory_Id")
	private SubCategory subCategory;

	@Column(name = "CreatedBy")
	private AdminUser createdBy;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

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

	public AdminUser getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(AdminUser createdBy) {
		this.createdBy = createdBy;
	}

}
