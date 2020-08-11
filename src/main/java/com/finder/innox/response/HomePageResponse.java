package com.finder.innox.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HomePageResponse implements Serializable {

	private static final long serialVersionUID = 8587602261745781473L;

	private List<BannerResponse> banners = new ArrayList<BannerResponse>();

	private List<CategoryResponse> categorys = new ArrayList<CategoryResponse>();

	private List<ProductResponse> popular_products = new ArrayList<ProductResponse>();

	private List<ProductResponse> promotion_products = new ArrayList<ProductResponse>();

	public List<BannerResponse> getBanners() {
		return banners;
	}

	public void setBanners(List<BannerResponse> banners) {
		this.banners = banners;
	}

	public List<CategoryResponse> getCategorys() {
		return categorys;
	}

	public void setCategorys(List<CategoryResponse> categorys) {
		this.categorys = categorys;
	}

	public List<ProductResponse> getPopular_products() {
		return popular_products;
	}

	public void setPopular_products(List<ProductResponse> popular_products) {
		this.popular_products = popular_products;
	}

	public List<ProductResponse> getPromotion_products() {
		return promotion_products;
	}

	public void setPromotion_products(List<ProductResponse> promotion_products) {
		this.promotion_products = promotion_products;
	}

}
