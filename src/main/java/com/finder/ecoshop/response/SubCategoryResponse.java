package com.finder.ecoshop.response;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import com.finder.ecoshop.core.dto.SubCategoryDTO;
import com.finder.ecoshop.utils.CommonUtil;

public class SubCategoryResponse implements Serializable {

	private static final long serialVersionUID = -602022960704251875L;

	private long sub_category_id;

	private String name;

	private int sequence_no;

	private String image_path;

	public SubCategoryResponse() {
		super();
	}

	public SubCategoryResponse(SubCategoryDTO dto, HttpServletRequest request) {
		super();
		this.sub_category_id = dto.getSeq();
		this.name = dto.getName();
		this.sequence_no = dto.getSequenceNo();
		this.image_path = CommonUtil.prepareImagePath(dto.getImagePath(), request);
	}

	public long getSub_category_id() {
		return sub_category_id;
	}

	public void setSub_category_id(long sub_category_id) {
		this.sub_category_id = sub_category_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSequence_no() {
		return sequence_no;
	}

	public void setSequence_no(int sequence_no) {
		this.sequence_no = sequence_no;
	}

	public String getImage_path() {
		return image_path;
	}

	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}

}
