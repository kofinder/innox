package com.finder.ecoshop.request;

import java.io.Serializable;

public class SubCategoryRequest implements Serializable {

	private static final long serialVersionUID = -199998844975692532L;

	private Long category_id;

	public Long getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}

}
