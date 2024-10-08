package com.finder.innox.core.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @author Kywee Zay
 *
 */
@Entity
@Table(name = "user")
public class User implements Serializable {

	private static final long serialVersionUID = 3041808915739574578L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userSeq;

	@Column(name = "user_name", unique = true)
	private String userName;

	@Column(name = "password")
	private String password;

	@Column(name = "email")
	private String email;

	@Column(name = "phone_no")
	private String phoneNo;

	@Column(name = "status")
	private Integer status;

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private Collection<Role> roles;

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

	@Column(name = "user_role_level")
	private Integer userRoleLevel;

	@Column(name = "device_token")
	private String deviceToken;

	@Column(name = "device_type")
	private Integer deviceType;

	public Long getUserSeq() {
		return userSeq;
	}

	public void setUserSeq(Long userSeq) {
		this.userSeq = userSeq;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
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

	public Integer getUserRoleLevel() {
		return userRoleLevel;
	}

	public void setUserRoleLevel(Integer userRoleLevel) {
		this.userRoleLevel = userRoleLevel;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}

	public Integer getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(Integer deviceType) {
		this.deviceType = deviceType;
	}

}
