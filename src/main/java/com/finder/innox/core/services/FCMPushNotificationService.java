package com.finder.innox.core.services;

import java.util.List;

import com.finder.innox.utils.NotificationDTO;

public interface FCMPushNotificationService {

	public void pushFCMNotification(final List<NotificationDTO> notificationList) throws Exception;

	public void pushFCMNOtification(NotificationDTO notification) throws Exception;

}
