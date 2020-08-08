package com.finder.ecoshop.request;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProductRequest implements Serializable {

	private static final long serialVersionUID = 4860117225476730578L;

	private Long product_id;

	private String searchKeyword;

	private BigDecimal startPrice;

	private BigDecimal endPrice;

	private Long category_id;

	private Long sub_category_id;

	public Long getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}

	public Long getSub_category_id() {
		return sub_category_id;
	}

	public void setSub_category_id(Long sub_category_id) {
		this.sub_category_id = sub_category_id;
	}

	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	public BigDecimal getStartPrice() {
		return startPrice;
	}

	public void setStartPrice(BigDecimal startPrice) {
		this.startPrice = startPrice;
	}

	public BigDecimal getEndPrice() {
		return endPrice;
	}

	public void setEndPrice(BigDecimal endPrice) {
		this.endPrice = endPrice;
	}

	public Long getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}

}
