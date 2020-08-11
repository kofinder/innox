package com.finder.innox.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProductListResponse implements Serializable {

	private static final long serialVersionUID = 2767479508911327777L;

	private List<ProductResponse> products = new ArrayList<ProductResponse>();

	public List<ProductResponse> getProducts() {
		return products;
	}

	public void setProducts(List<ProductResponse> products) {
		this.products = products;
	}

}
