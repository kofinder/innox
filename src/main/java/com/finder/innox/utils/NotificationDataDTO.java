package com.finder.innox.utils;

public class NotificationDataDTO {

	private Long id;
	private String orderNo;
	private Integer orderStatus;
	private String orderStatusDesc;
	private String title;
	private String message;
	private Integer notificationType;
	private String notificationTypeDesc;
	private String playStoreUrl;
	private String directDownloadUrl;
	private String appStoreUrl;
	private String body;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderStatusDesc() {
		return orderStatusDesc;
	}

	public void setOrderStatusDesc(String orderStatusDesc) {
		this.orderStatusDesc = orderStatusDesc;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(Integer notificationType) {
		this.notificationType = notificationType;
	}

	public String getPlayStoreUrl() {
		return playStoreUrl;
	}

	public void setPlayStoreUrl(String playStoreUrl) {
		this.playStoreUrl = playStoreUrl;
	}

	public String getDirectDownloadUrl() {
		return directDownloadUrl;
	}

	public void setDirectDownloadUrl(String directDownloadUrl) {
		this.directDownloadUrl = directDownloadUrl;
	}

	public String getAppStoreUrl() {
		return appStoreUrl;
	}

	public void setAppStoreUrl(String appStoreUrl) {
		this.appStoreUrl = appStoreUrl;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getNotificationTypeDesc() {
		return notificationTypeDesc;
	}

	public void setNotificationTypeDesc(String notificationTypeDesc) {
		this.notificationTypeDesc = notificationTypeDesc;
	}

}
