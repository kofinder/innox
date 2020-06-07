package com.finder.ecoshop.core.domain;

import java.io.Serializable;

import javax.persistence.Column;

public class SubCategory extends CommonEntity implements Serializable {

	private static final long serialVersionUID = 5206892789313763641L;

	@Column(name = "Name")
	private String name;

	@Column(name = "ImagePath")
	private String imagePath;

	@Column(name = "Sequence")
	private Integer sequence;

	@Column(name = "Status")
	private Integer status;

	@Column(name = "Category_Id")
	private Category category;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
