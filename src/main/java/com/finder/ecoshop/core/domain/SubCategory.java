package com.finder.ecoshop.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sub_category")
public class SubCategory extends CommonEntity implements Serializable {

	private static final long serialVersionUID = 2799110511713961866L;

	@Column(name = "Name")
	private String name;

	@Column(name = "ImagePath")
	private String imagePath;

	@Column(name = "Sequence")
	private Integer sequence;

	@Column(name = "Status")
	private Integer status;

	@ManyToOne
	@JoinColumn(name = "Category_Id")
	private Category category;

	@ManyToOne
	@JoinColumn(name = "CreatedBy_Id")
	private AdminUser createdBy;

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

	public AdminUser getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(AdminUser createdBy) {
		this.createdBy = createdBy;
	}

}
