package com.finder.innox.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.finder.innox.utils.FieldError;

public class Response<T> implements Serializable {

	private static final long serialVersionUID = -9191074482660386948L;

	private String responseCode;

	private String responseMessage;

	private T data;

	private List<FieldError> errorList;

	public Response() {
		super();
		this.responseCode = "1";
		this.responseMessage = "";
		this.errorList = new ArrayList<FieldError>();
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

	public List<FieldError> getErrorList() {
		return errorList;
	}

	public void setErrorList(List<FieldError> errorList) {
		this.errorList = errorList;
	}

}
