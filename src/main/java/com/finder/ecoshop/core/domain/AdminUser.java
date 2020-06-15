package com.finder.ecoshop.core.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Kywee Zay
 *
 */
@Entity
@Table(name = "user")
public class AdminUser implements Serializable {

	private static final long serialVersionUID = 3041808915739574578L;

	@Id
	@Column(name = "id")
	@GeneratedValue()
	private Integer userSeq;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "password")
	private String password;

	@Column(name = "email")
	private String email;

	@Column(name = "status")
	private String status;

	@Column(name = "roles")
	private String roles;

	@Column(name = "avatar")
	private String avatar;

	@Column(name = "draft_flag")
	private Boolean draftFlag;

	@Column(name = "record_reg_seq")
	private Integer recordRegSeq;

	@Column(name = "record_update_seq")
	private Integer recordUpdateSeq;

	@Column(name = "record_reg_date")
	private Date recordRegDate;

	@Column(name = "record_upd_date")
	private Date recordUpdDate;

	@Column(name = "record_tmp_flag")
	private Boolean recordTmpFlag;

	@Column(name = "record_del_flag")
	private Boolean recordDelFlag;

	public Integer getUserSeq() {
		return userSeq;
	}

	public void setUserSeq(Integer userSeq) {
		this.userSeq = userSeq;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Boolean getDraftFlag() {
		return draftFlag;
	}

	public void setDraftFlag(Boolean draftFlag) {
		this.draftFlag = draftFlag;
	}

	public Integer getRecordRegSeq() {
		return recordRegSeq;
	}

	public void setRecordRegSeq(Integer recordRegSeq) {
		this.recordRegSeq = recordRegSeq;
	}

	public Integer getRecordUpdateSeq() {
		return recordUpdateSeq;
	}

	public void setRecordUpdateSeq(Integer recordUpdateSeq) {
		this.recordUpdateSeq = recordUpdateSeq;
	}

	public Date getRecordRegDate() {
		return recordRegDate;
	}

	public void setRecordRegDate(Date recordRegDate) {
		this.recordRegDate = recordRegDate;
	}

	public Date getRecordUpdDate() {
		return recordUpdDate;
	}

	public void setRecordUpdDate(Date recordUpdDate) {
		this.recordUpdDate = recordUpdDate;
	}

	public Boolean getRecordTmpFlag() {
		return recordTmpFlag;
	}

	public void setRecordTmpFlag(Boolean recordTmpFlag) {
		this.recordTmpFlag = recordTmpFlag;
	}

	public Boolean getRecordDelFlag() {
		return recordDelFlag;
	}

	public void setRecordDelFlag(Boolean recordDelFlag) {
		this.recordDelFlag = recordDelFlag;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
