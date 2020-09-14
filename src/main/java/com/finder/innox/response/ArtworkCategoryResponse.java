package com.finder.innox.response;

import java.io.Serializable;

public class ArtworkCategoryResponse implements Serializable {

	private static final long serialVersionUID = -3695447382925647554L;

	private long category_id;

	private String category_name;

	private String category_code;
	
	


	public long getCategory_id() {
		return category_id;
	}

	public void setCategory_id(long category_id) {
		this.category_id = category_id;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String getCategory_code() {
		return category_code;
	}

	public void setCategory_code(String category_code) {
		this.category_code = category_code;
	}

}
