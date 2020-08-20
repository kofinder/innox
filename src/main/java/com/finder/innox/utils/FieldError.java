package com.finder.innox.utils;

public class FieldError {

	private String fieldCode;

	private String errorMessage;

	public FieldError(String fieldErrorCode, String errorMessage) {
		super();
		this.fieldCode = fieldErrorCode;
		this.errorMessage = errorMessage;
	}

	public enum FieldCode {
		CUSTOMER_ID("2000"), PAGE_NO("2001"), CATEGORY_ID("2002"), SUB_CATEGORY_ID("2003"), PRODUCT_ID("2004"),
		ARTWORK("2005"), DESIGNER("2006"), ARTWORK_CATEGORY("2007");

		private String code;

		public String getCode() {
			return code;
		}

		private FieldCode(String code) {
			this.code = code;
		}
	}

	public enum ErrorMessage {

		CUSTOMER_ID_REQUIRED("Customer id is required"), PAGE_NO_REQUIRED("Page no is required"),
		CATEGORY_ID_REQUIRED("Category id is required"), SUB_CATEGORY_ID_REQUIRED("Sub category id is required"),
		PRODUCT_ID_REQUIRED("Product id is required"), DESIGNER_ID_REQUIRED("Designer id is required"),
		ARTWORK_NAME_REQUIRED("Arwork name is required"), ARTWORK_PRICE_REQUIRED("Artwork price is required"),
		ARTWORK_IMAGE_REQUIRED("Artwork image is required"), ARTWORK_CATEGORY_REQUIRED("Artwork category is required");

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
