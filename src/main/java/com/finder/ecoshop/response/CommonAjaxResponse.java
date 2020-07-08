package com.finder.ecoshop.response;

import java.io.Serializable;

public class CommonAjaxResponse<T> implements Serializable {

	private static final long serialVersionUID = -7266714679915473211L;

	private String responseCode;

	private String responseMessage;

	private T data;

	public CommonAjaxResponse() {
		super();
		this.responseCode = "1";
		this.responseMessage = "";
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
