package com.finder.innox.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "brand")
public class Brand extends CommonEntity implements Serializable {

	private static final long serialVersionUID = -1263364244620289034L;

	@Column(name = "name")
	private String name;

	@Column(name = "image_path")
	private String imagePath;

	@Column(name = "sequence_no")
	private Integer sequence;

	@Column(name = "status")
	private Integer status;

	@ManyToOne
	@JoinColumn(name = "created_by_id")
	private User createdBy;

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

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

}
