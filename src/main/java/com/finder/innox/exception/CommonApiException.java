package com.finder.innox.exception;

import org.springframework.http.HttpStatus;

public class CommonApiException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private HttpStatus httpStatus;
	private int status;
	private String developerMessage;
	private String userMessage;

	public CommonApiException() {

	}

	public CommonApiException(String developerMessage) {
		super(developerMessage);
	}

	public CommonApiException(Throwable cause) {
		super(cause);
	}

	public CommonApiException(String developerMessage, Throwable cause) {
		super(developerMessage, cause);
	}

	public CommonApiException(int status, String developerMessage, String userMessage) {
		this.userMessage = userMessage;
		this.developerMessage = developerMessage;
		this.status = status;
	}

	public CommonApiException(int status, String developerMessage, String userMessage, HttpStatus httpStatus) {
		this.userMessage = userMessage;
		this.developerMessage = developerMessage;
		this.status = status;
		this.httpStatus = httpStatus;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDeveloperMessage() {
		return developerMessage;
	}

	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}

	public String getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}

}
