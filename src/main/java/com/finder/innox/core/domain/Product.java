package com.finder.innox.core.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

	@ManyToOne
	@JoinColumn(name = "created_by_id")
	private User createdBy;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
	private List<ProductImage> productImageList;

	@Column(name = "image_path1")
	private String imagePath1;

	@Column(name = "image_path2")
	private String imagePath2;

	@Column(name = "image_path3")
	private String imagePath3;

	@Column(name = "image_path4")
	private String imagePath4;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
	private List<ProductColor> productColorList;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
	private List<ProductSize> productSizeList;

	@Column(name = "is_custom_product")
	private Integer isCustomProduct;

	@ManyToOne
	@JoinColumn(name = "custom_product_id")
	private CustomProduct customProduct;

	@ManyToOne
	@JoinColumn(name = "custom_product_item_id")
	private CustomItem customProductItem;

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

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
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

	public List<ProductImage> getProductImageList() {
		return productImageList;
	}

	public void setProductImageList(List<ProductImage> productImageList) {
		this.productImageList = productImageList;
	}

	public String getImagePath1() {
		return imagePath1;
	}

	public void setImagePath1(String imagePath1) {
		this.imagePath1 = imagePath1;
	}

	public String getImagePath2() {
		return imagePath2;
	}

	public void setImagePath2(String imagePath2) {
		this.imagePath2 = imagePath2;
	}

	public String getImagePath3() {
		return imagePath3;
	}

	public void setImagePath3(String imagePath3) {
		this.imagePath3 = imagePath3;
	}

	public String getImagePath4() {
		return imagePath4;
	}

	public void setImagePath4(String imagePath4) {
		this.imagePath4 = imagePath4;
	}

	public Integer getIsCustomProduct() {
		return isCustomProduct;
	}

	public void setIsCustomProduct(Integer isCustomProduct) {
		this.isCustomProduct = isCustomProduct;
	}

	public CustomProduct getCustomProduct() {
		return customProduct;
	}

	public void setCustomProduct(CustomProduct customProduct) {
		this.customProduct = customProduct;
	}

	public List<ProductColor> getProductColorList() {
		return productColorList;
	}

	public void setProductColorList(List<ProductColor> productColorList) {
		this.productColorList = productColorList;
	}

	public List<ProductSize> getProductSizeList() {
		return productSizeList;
	}

	public void setProductSizeList(List<ProductSize> productSizeList) {
		this.productSizeList = productSizeList;
	}

	public CustomItem getCustomProductItem() {
		return customProductItem;
	}

	public void setCustomProductItem(CustomItem customProductItem) {
		this.customProductItem = customProductItem;
	}

}
