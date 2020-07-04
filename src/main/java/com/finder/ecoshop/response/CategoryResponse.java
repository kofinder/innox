package com.finder.ecoshop.response;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import com.finder.ecoshop.core.dto.CategoryDTO;
import com.finder.ecoshop.utils.CommonUtil;

public class CategoryResponse implements Serializable {

	private static final long serialVersionUID = 2051740284500720261L;

	private long category_id;

	private String category_name;

	private String image_path;

	private int sequence_no;

	public CategoryResponse() {
		super();
	}

	public CategoryResponse(CategoryDTO dto, HttpServletRequest request) {
		this.category_id = dto.getSeq();
		this.category_name = dto.getName();
		this.image_path = CommonUtil.prepareImagePath(dto.getImagePath(), request);
		this.sequence_no = dto.getSequenceNo();
	}

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

	public String getImage_path() {
		return image_path;
	}

	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}

	public int getSequence_no() {
		return sequence_no;
	}

	public void setSequence_no(int sequence_no) {
		this.sequence_no = sequence_no;
	}

}
