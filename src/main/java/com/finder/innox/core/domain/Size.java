package com.finder.innox.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "size")
public class Size extends CommonEntity implements Serializable {

	private static final long serialVersionUID = 6122240162965879724L;

	@Column(name = "size_code")
	private String sizeCode;

	@Column(name = "size_name")
	private String sizeName;

	@ManyToOne
	@JoinColumn(name = "created_by_id")
	private User createdBy;

	@Column(name = "Status")
	private Integer status;

	@Column(name = "size_category")
	private Integer sizeCategory;

	public String getSizeCode() {
		return sizeCode;
	}

	public void setSizeCode(String sizeCode) {
		this.sizeCode = sizeCode;
	}

	public String getSizeName() {
		return sizeName;
	}

	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getSizeCategory() {
		return sizeCategory;
	}

	public void setSizeCategory(Integer sizeCategory) {
		this.sizeCategory = sizeCategory;
	}

}
