package com.finder.innox.response;

import java.io.Serializable;

public class UserResponse implements Serializable {

	private static final long serialVersionUID = 5472261686341628132L;

	private long user_id;

	private String name;

	private String phone_no;

	private long state_id;

	private String state_name;

	private long township_id;

	private String township_name;

	private String detail_address;

	private String remark;

	public UserResponse() {
		super();
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone_no() {
		return phone_no;
	}

	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
