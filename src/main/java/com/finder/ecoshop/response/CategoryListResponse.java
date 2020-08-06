package com.finder.ecoshop.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CategoryListResponse implements Serializable {

	private static final long serialVersionUID = 7968829112061951799L;

	private List<CategoryResponse> category_list = new ArrayList<CategoryResponse>();

	public List<CategoryResponse> getCategory_list() {
		return category_list;
	}

	public void setCategory_list(List<CategoryResponse> category_list) {
		this.category_list = category_list;
	}

}
