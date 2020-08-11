package com.finder.innox.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CategoryListResponse implements Serializable {

	private static final long serialVersionUID = 7968829112061951799L;

	private List<CategoryResponse> categorys = new ArrayList<CategoryResponse>();

	public List<CategoryResponse> getCategorys() {
		return categorys;
	}

	public void setCategorys(List<CategoryResponse> categorys) {
		this.categorys = categorys;
	}

}
