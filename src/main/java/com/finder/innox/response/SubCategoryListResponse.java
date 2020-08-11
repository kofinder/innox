package com.finder.innox.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SubCategoryListResponse implements Serializable {

	private static final long serialVersionUID = 7997719426821048218L;

	private long category_id;

	private List<SubCategoryResponse> sub_categorys = new ArrayList<SubCategoryResponse>();

	public long getCategory_id() {
		return category_id;
	}

	public void setCategory_id(long category_id) {
		this.category_id = category_id;
	}

	public List<SubCategoryResponse> getSub_categroys() {
		return sub_categorys;
	}

	public void setSub_categroys(List<SubCategoryResponse> sub_categroys) {
		this.sub_categorys = sub_categroys;
	}

}
