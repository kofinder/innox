package com.finder.ecoshop.core.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class CommonEntity implements Serializable {

	private static final long serialVersionUID = -180051314888881215L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Seq")
	private Long Seq;

	@Column(name = "CreatedTime", updatable = false)
	private Date createdTime;

	@Column(name = "UpdatedTime")
	private Date updatedTime;

	public Long getSeq() {
		return Seq;
	}

	public void setSeq(Long seq) {
		Seq = seq;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

}
