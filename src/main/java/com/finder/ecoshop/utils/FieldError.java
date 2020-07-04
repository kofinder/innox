package com.finder.ecoshop.utils;

public class FieldError {

	private String fieldCode;

	private String errorMessage;
	
	public FieldError(String fieldErrorCode, String errorMessage) {
		super();
		this.fieldCode = fieldErrorCode;
		this.errorMessage = errorMessage;
	}

	public enum FieldCode {
		CUSTOMER_ID("2000"), PAGE_NO("20001");

		private String code;

		

		public String getCode() {
			return code;
		}



		private FieldCode(String code) {
			this.code = code;
		}
	}

	public enum ErrorMessage {

		CUSTOMER_ID_REQUIRED("Customer id is required"), PAGE_NO_REQUIRED("Page no is required");

		private String message;

		public String getMessage() {
			return message;
		}

		private ErrorMessage(String message) {
			this.message = message;
		}
	}

	public String getFieldCode() {
		return fieldCode;
	}

	public void setFieldCode(String fieldCode) {
		this.fieldCode = fieldCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
