package com.finder.innox.request;

import java.io.Serializable;

public class DeviceTokenUpdateRequest implements Serializable {

	private static final long serialVersionUID = 4606078442107556158L;

	private long user_id;

	private String device_token;

	private int device_type;

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public String getDevice_token() {
		return device_token;
	}

	public void setDevice_token(String device_token) {
		this.device_token = device_token;
	}

	public int getDevice_type() {
		return device_type;
	}

	public void setDevice_type(int device_type) {
		this.device_type = device_type;
	}

}
