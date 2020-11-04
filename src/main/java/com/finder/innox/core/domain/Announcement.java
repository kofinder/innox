package com.finder.innox.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "announcement")

public class Announcement extends CommonEntity implements Serializable {
	private static final long serialVersionUID = -8859715003227826414L;

	@Column(name = "noti_type")
	private Integer notiType;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@Column(name = "summary_image")
	private String summaryImage;

	@Column(name = "detail_image")
	private String detailImage;

	@ManyToOne
	@JoinColumn(name = "created_by")
	private User createdBy;

	@Column(name = "status")
	private Integer status;

	public Integer getNotiType() {
		return notiType;
	}

	public void setNotiType(Integer notiType) {
		this.notiType = notiType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSummaryImage() {
		return summaryImage;
	}

	public void setSummaryImage(String summaryImage) {
		this.summaryImage = summaryImage;
	}

	public String getDetailImage() {
		return detailImage;
	}

	public void setDetailImage(String detailImage) {
		this.detailImage = detailImage;
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

}
