package com.finder.innox.response;

import java.io.Serializable;

public class UserRegisterResponse implements Serializable {

	private static final long serialVersionUID = 3500087482786379047L;

	private Long user_id;

	private String user_name;

	private String email;

	private String phoneNo;

	private int user_role_level;

	private String user_role_level_text;

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
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

	public int getUser_role_level() {
		return user_role_level;
	}

	public void setUser_role_level(int user_role_level) {
		this.user_role_level = user_role_level;
	}

	public String getUser_role_level_text() {
		return user_role_level_text;
	}

	public void setUser_role_level_text(String user_role_level_text) {
		this.user_role_level_text = user_role_level_text;
	}

}
