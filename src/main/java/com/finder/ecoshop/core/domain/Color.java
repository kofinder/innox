package com.finder.ecoshop.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "color")
public class Color extends CommonEntity implements Serializable {

	private static final long serialVersionUID = -7097234728569290157L;

	@Column(name = "color_code")
	private String colorCode;

	@Column(name = "color_name")
	private String colorName;

	@Column(name = "created_by_id")
	private AdminUser createdBy;

	public String getColorCode() {
		return colorCode;
	}

	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public AdminUser getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(AdminUser createdBy) {
		this.createdBy = createdBy;
	}

}
