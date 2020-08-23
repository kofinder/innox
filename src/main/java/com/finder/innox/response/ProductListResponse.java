package com.finder.innox.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProductListResponse implements Serializable {

	private static final long serialVersionUID = 2767479508911327777L;

	private int total_count;

	private boolean has_more_list;

	public int getTotal_count() {
		return total_count;
	}

	public void setTotal_count(int total_count) {
		this.total_count = total_count;
	}

	public boolean isHas_more_list() {
		return has_more_list;
	}

	public void setHas_more_list(boolean has_more_list) {
		this.has_more_list = has_more_list;
	}

	private List<ProductResponse> products = new ArrayList<ProductResponse>();

	public List<ProductResponse> getProducts() {
		return products;
	}

	public void setProducts(List<ProductResponse> products) {
		this.products = products;
	}

}
