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
		ARTWORK("2005"), DESIGNER("2006"), ARTWORK_CATEGORY("2007"), CUSTOM_PRODUCT_ID("2008"), USER_NAME("2009"),
		PHONE_NO("2010"), PASSWORD("2011"), COLOR_ID("2012"), SIZE_ID("2013"), QUANTITY_REQUIRED("2014"),
		SHOPPING_CART_ID("2015"), USER_ROLE("2016"), STATE_ID("2017"), TOWNSHIP("2018"), ADDRESS("2019"),
		DUPLICATE_NAME("2020");

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
		ARTWORK_IMAGE_REQUIRED("Artwork image is required"), ARTWORK_CATEGORY_REQUIRED("Artwork category is required"),
		CUSTOM_PRODUCT_ID_REQUIRED("Custom product is is required"), USER_NAME_REQUIRED("User name is required"),
		PHONE_NO_REQUIRED("Phone no is required"), PASSWORD_REQUIRED("Password is required"),
		PASSWORD_NOT_MATCH("Password does not match"), COLOR_ID_REQUIRED("Color is is required"),
		SIZE_ID_REQUIRED("Size id is required"), QUANTITY_REQUIRED("Quantity is required"),
		SHOPPING_CART_ID_REQUIRED("Shopping cart id is required"), USER_ROLE_REQUIRED("User role is required"),
		STATE_ID_REQUIRED("State id is required"), TOWNSHIP_ID_REQUIRED("Township id is reduired"),
		ADDRESS_REQUIRED("Detail address is required"), DUPLICATE_NAME("User name is already use by someone!");

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
