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
		DUPLICATE_NAME("2020"), CUSTOM_ITEM_ID("2021"), CUSTOM_PRODUCT_LAYOUT("2022"), PRODUCT_FONTS_ID("2023"),
		PRODUCT_SIZE_ID("2024"), PAYMENT_TYPE_CODE("2025"), ORDER_STATUS("2026"), ORDER("2027"),
		PRODUCT_OUT_OF_STOCK("2028"), DEVICE_TYPE("2029"), DEVICE_TOKEN("2030"), ANNOUNCEMENT_ID("2031"),
		DUPLICATED_PHONE_NO("2032");

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
		ADDRESS_REQUIRED("Detail address is required"), DUPLICATE_NAME("User name is already use by someone!"),
		CUSTOM__ITEM_REQUIRED("Custom item id is required!"),
		CUSTOM_PRODUCT_LAYOUT_REQUIRED("Custom item layout id is required!"), FONT_ID_REQUIRED("Font id is required"),
		ARTWORK_ID_REQUIRED("Artwork id is required!"), CUSTOM_PRODUCT_LAYOUT_LIST("Custom item layout is required"),
		CUSTOM_LAYOUT_IMAGE_REQUIRED("Custom layout image is required"),
		PAYMENT_TYPE_CODE_REQUIRED("Payment type code is required"), ORDER_STATUS_REQUIRED("Order status is required"),
		ORDER_ID_REQUIRED("Order id is is required"), PRODUCT_OUT_OF_STOCK("Product is out of stock"),
		DEVICE_TYPE_REQUIRED("Device type is required"), DEVICE_TOKEN_REQUIRED("Device token is required"),
		ANNOUNCEMENT_ID_REQUIRED("Announcement id is required"),
		DUPLICATED_PHONE_NO("Phone no is already use by someone!");

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
