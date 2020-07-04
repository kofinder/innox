package com.finder.ecoshop.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProductListResponse implements Serializable {

	private static final long serialVersionUID = 2767479508911327777L;
	
	private List<ProductResponse> product_list = new ArrayList<ProductResponse>();

	public List<ProductResponse> getProduct_list() {
		return product_list;
	}

	public void setProduct_list(List<ProductResponse> product_list) {
		this.product_list = product_list;
	}

}
