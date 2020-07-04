package com.finder.ecoshop.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HomePageResponse implements Serializable {

	private static final long serialVersionUID = 8587602261745781473L;

	private List<BannerResponse> banner_list = new ArrayList<BannerResponse>();

	private List<CategoryResponse> category_list = new ArrayList<CategoryResponse>();

	private List<ProductResponse> product_list = new ArrayList<ProductResponse>();

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

	public List<ProductResponse> getProduct_list() {
		return product_list;
	}

	public void setProduct_list(List<ProductResponse> product_list) {
		this.product_list = product_list;
	}

}
