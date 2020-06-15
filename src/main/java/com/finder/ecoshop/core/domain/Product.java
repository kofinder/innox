package com.finder.ecoshop.core.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;;

@Entity
@Table(name = "product")
public class Product extends CommonEntity implements Serializable {

	private static final long serialVersionUID = -1863957211918543136L;

	@ManyToOne
	@JoinColumn(name = "brand_id")
	private Brand brand;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	@ManyToOne
	@JoinColumn(name = "sub_category_id")
	private SubCategory subCategory;

	@Column(name = "name")
	private String name;

	@Column(name = "code_number")
	private String codeNumber;

	@Column(name = "price")
	private BigDecimal price;

	@Column(name = "original_price")
	private BigDecimal originalPrice;

	@Column(name = "discount_percent")
	private Double discountPercent;

	@Column(name = "quantity")
	private Integer quantity;

	@Column(name = "is_promotion")
	private Boolean isPromotion;

	@Column(name = "is_new_arrival")
	private Boolean isNewArrival;

	@Column(name = "is_popular")
	private Boolean isPopular;

	@Column(name = "status")
	private Integer status;

	@Column(name = "overview")
	private String overview;

	@Column(name = "detail")
	private String detail;

	@Column(name = "created_by_id")
	private AdminUser createdBy;

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCodeNumber() {
		return codeNumber;
	}

	public void setCodeNumber(String codeNumber) {
		this.codeNumber = codeNumber;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(BigDecimal originalPrice) {
		this.originalPrice = originalPrice;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Boolean getIsPromotion() {
		return isPromotion;
	}

	public void setIsPromotion(Boolean isPromotion) {
		this.isPromotion = isPromotion;
	}

	public Boolean getIsNewArrival() {
		return isNewArrival;
	}

	public void setIsNewArrival(Boolean isNewArrival) {
		this.isNewArrival = isNewArrival;
	}

	public Boolean getIsPopular() {
		return isPopular;
	}

	public void setIsPopular(Boolean isPopular) {
		this.isPopular = isPopular;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public AdminUser getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(AdminUser createdBy) {
		this.createdBy = createdBy;
	}

	public Double getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(Double discountPercent) {
		this.discountPercent = discountPercent;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

}
