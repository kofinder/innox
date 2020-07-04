package com.finder.ecoshop.core.domain;

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
	private AdminUser createdBy;

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

	public AdminUser getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(AdminUser createdBy) {
		this.createdBy = createdBy;
	}

}
