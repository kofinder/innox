package com.finder.innox.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SubCategoryListResponse implements Serializable {

	private static final long serialVersionUID = 7997719426821048218L;

	private long category_id;

	private List<SubCategoryResponse> sub_categroy_list = new ArrayList<SubCategoryResponse>();

	public long getCategory_id() {
		return category_id;
	}

	public void setCategory_id(long category_id) {
		this.category_id = category_id;
	}

	public List<SubCategoryResponse> getSub_categroy_list() {
		return sub_categroy_list;
	}

	public void setSub_categroy_list(List<SubCategoryResponse> sub_categroy_list) {
		this.sub_categroy_list = sub_categroy_list;
	}

}
