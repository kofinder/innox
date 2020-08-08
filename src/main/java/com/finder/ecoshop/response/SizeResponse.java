package com.finder.ecoshop.response;

import java.io.Serializable;

import com.finder.ecoshop.core.dto.SizeDTO;

public class SizeResponse implements Serializable {

	private static final long serialVersionUID = -7830763128199996147L;

	private long size_id;

	private String size_name;

	private String size_code;

	public SizeResponse() {
		super();
	}

	public SizeResponse(SizeDTO size) {
		super();
		this.size_id = size.getSeq();
		this.size_name = size.getSizeName();
		this.size_code = size.getSizeCode();
	}

	public long getSize_id() {
		return size_id;
	}

	public void setSize_id(long size_id) {
		this.size_id = size_id;
	}

	public String getSize_name() {
		return size_name;
	}

	public void setSize_name(String size_name) {
		this.size_name = size_name;
	}

	public String getSize_code() {
		return size_code;
	}

	public void setSize_code(String size_code) {
		this.size_code = size_code;
	}

}
