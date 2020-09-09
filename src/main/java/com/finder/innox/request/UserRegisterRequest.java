package com.finder.innox.request;

import java.io.Serializable;

public class UserRegisterRequest implements Serializable {

	private static final long serialVersionUID = 8814422415670402003L;

	private String name;

	private String email;

	private String phoneNo;

	private String password;

	private String confirm_password;

	private Long state_id;

	private Long township_id;

	private String detail_address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getConfirm_password() {
		return confirm_password;
	}

	public void setConfirm_password(String confirm_password) {
		this.confirm_password = confirm_password;
	}

	public Long getState_id() {
		return state_id;
	}

	public void setState_id(Long state_id) {
		this.state_id = state_id;
	}

	public Long getTownship_id() {
		return township_id;
	}

	public void setTownship_id(Long township_id) {
		this.township_id = township_id;
	}

	public String getDetail_address() {
		return detail_address;
	}

	public void setDetail_address(String detail_address) {
		this.detail_address = detail_address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
