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

	private String jwt_token;

	private String profile_image;

	private long state_id;

	private String state_name;

	private long township_id;

	private String township_name;

	private String detail_address;

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

	public String getJwt_token() {
		return jwt_token;
	}

	public void setJwt_token(String jwt_token) {
		this.jwt_token = jwt_token;
	}

	public String getProfile_image() {
		return profile_image;
	}

	public void setProfile_image(String profile_image) {
		this.profile_image = profile_image;
	}

	public long getState_id() {
		return state_id;
	}

	public void setState_id(long state_id) {
		this.state_id = state_id;
	}

	public long getTownship_id() {
		return township_id;
	}

	public void setTownship_id(long township_id) {
		this.township_id = township_id;
	}

	public String getDetail_address() {
		return detail_address;
	}

	public void setDetail_address(String detail_address) {
		this.detail_address = detail_address;
	}

	public String getState_name() {
		return state_name;
	}

	public void setState_name(String state_name) {
		this.state_name = state_name;
	}

	public String getTownship_name() {
		return township_name;
	}

	public void setTownship_name(String township_name) {
		this.township_name = township_name;
	}

}
