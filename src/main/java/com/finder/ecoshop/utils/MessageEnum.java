package com.finder.ecoshop.utils;

public enum MessageEnum {

	LOGIN_FIAL("UserName or Password is incorrect"),
	LOGIN_FIAL_WITH_PHONE_NUMBER("Phone Number or Password is incorrect"),
	USER_NAME_EXIST("User Name is already exist!"),
	ADVERTISER_EXIST("Advertiser is already exist!"),
	BLOG_EXIST("Blog is already exist!"),
	BRAND_EXIST("Brand is already exist!"),
	STATE_EXIST("State is already exist!"),
	CITY_EXIST("City is already exist!"),
	TOWNSHIP_EXIST("Township is already exist!"),
	ERROR_MESSAGE("Oops, something wrong!"),
	SUCCESS_MESSAGE("You can open and see participants list!"),
	ALERT_MESSAGE("Participants list is empty!"),
	BOTH_NAME_CODE_EXIST("Both name and code number are already exist!"),
	BOTH_NAME_CODE_EXIST_FOR_THIS_TYPE("Both name and code number are already exist for this type!"),
	SAVE_SUCCESS("Saved Successfully."),
	UPDATE_SUCCESS("Updated Successfully."),
	UPDATE_FAILED("Updating Failed !."),
	SAVE_FAILED("Saving Failed !"),
	PHONE_NUMBER_EXIST("Phone number is already exist!"),
	TITLE_EXIST("Title is already exist!"),
	LAYOUT_NAME_EXIST("Item layout is alread exist");
	
	
	private String desc;
	
	private MessageEnum(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
