package com.finder.innox.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "artwork_category")
public class ArtworkCategory extends CommonEntity implements Serializable {

	private static final long serialVersionUID = 3478417073631343658L;

	@Column(name = "category_name")
	private String categoryName;

	@Column(name = "category_code")
	private String cateogryCode;

	@Column(name = "status")
	private Integer status;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCateogryCode() {
		return cateogryCode;
	}

	public void setCateogryCode(String cateogryCode) {
		this.cateogryCode = cateogryCode;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
