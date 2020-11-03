package com.finder.innox.utils;

public class NotificationDTO {

	private String to;
	private NotificationDataDTO data;
	private NotificationDataDTO notification;

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public NotificationDataDTO getData() {
		return data;
	}

	public void setData(NotificationDataDTO data) {
		this.data = data;
	}

	public NotificationDataDTO getNotification() {
		return notification;
	}

	public void setNotification(NotificationDataDTO notification) {
		this.notification = notification;
	}

}
