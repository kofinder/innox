package com.finder.innox.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HomePageResponse implements Serializable {

	private static final long serialVersionUID = 8587602261745781473L;

	private List<BannerResponse> banner_list = new ArrayList<BannerResponse>();

	private List<CategoryResponse> category_list = new ArrayList<CategoryResponse>();

	private List<ProductResponse> popular_product_list = new ArrayList<ProductResponse>();

	private List<ProductResponse> promotion_product_list = new ArrayList<ProductResponse>();

	public List<BannerResponse> getBanner_list() {
		return banner_list;
	}

	public void setBanner_list(List<BannerResponse> banner_list) {
		this.banner_list = banner_list;
	}

	public List<CategoryResponse> getCategory_list() {
		return category_list;
	}

	public void setCategory_list(List<CategoryResponse> category_list) {
		this.category_list = category_list;
	}

	public List<ProductResponse> getPopular_product_list() {
		return popular_product_list;
	}

	public void setPopular_product_list(List<ProductResponse> popular_product_list) {
		this.popular_product_list = popular_product_list;
	}

	public List<ProductResponse> getPromotion_product_list() {
		return promotion_product_list;
	}

	public void setPromotion_product_list(List<ProductResponse> promotion_product_list) {
		this.promotion_product_list = promotion_product_list;
	}

}
