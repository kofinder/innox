package com.finder.innox.core.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finder.innox.core.domain.Announcement;
import com.finder.innox.core.domain.User;
import com.finder.innox.core.dto.AnnouncementDTO;
import com.finder.innox.core.dto.UserDTO;
import com.finder.innox.core.services.AnnouncementService;
import com.finder.innox.core.services.FCMPushNotificationService;
import com.finder.innox.repository.AnnouncementDao;
import com.finder.innox.repository.UserDao;
import com.finder.innox.utils.CommonConstant;
import com.finder.innox.utils.CommonStatus;
import com.finder.innox.utils.CommonUtil;
import com.finder.innox.utils.DeviceTypeEnum;
import com.finder.innox.utils.ImagesUtil;
import com.finder.innox.utils.NotificationDTO;
import com.finder.innox.utils.NotificationDataDTO;
import com.finder.innox.utils.NotificationTypeEnum;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AnnouncementDao announcementDao;

	@Autowired
	private FCMPushNotificationService fcmService;

	@Autowired
	private UserDao userDao;

	@Override
	public AnnouncementDTO getDataById(long announcementId) {
		logger.info("getDataById() >> Announcement Id : " + announcementId);
		Announcement announcement = announcementDao.get(announcementId);
		if (announcement != null) {
			return new AnnouncementDTO(announcement);
		}
		return new AnnouncementDTO();
	}

	@Override
	public AnnouncementDTO manageAnnouncement(AnnouncementDTO announcementDTO) throws Exception {
		logger.info("manageAnnouncement() >>> Start");

		Announcement announcement = null;
		if (announcementDTO.getSeq() > 0) {
			// update
			logger.info("manageAnnouncement() >>> Announcement Id : " + announcementDTO.getSeq());
			announcement = announcementDao.get(announcementDTO.getSeq());
			announcement.setUpdatedTime(new Date());
		} else {
			// save
			announcement = new Announcement();
			announcement.setCreatedTime(new Date());
		}

		announcement.setNotiType(announcementDTO.getNotiType());
		announcement.setTitle(announcementDTO.getTitle());
		announcement.setDescription(announcementDTO.getDescription());

		User createdBy = new User();
		createdBy.setUserSeq(announcementDTO.getUserDTO().getSeq());
		announcement.setCreatedBy(createdBy);

		announcement.setStatus(announcementDTO.getStatus());

		announcementDao.saveOrUpdate(announcement);

		if (!CommonUtil.isEmpty(announcementDTO.getSummaryImageFile().getOriginalFilename())) {
			try {
				String imageName = ImagesUtil.uploadMultipartFile(announcementDTO.getSummaryImageFile(),
						CommonConstant.ANNOUNCEMENT_DIRECTORY + CommonConstant.ANNOUNCEMENT_IMAGE_DIRECTORY
								+ announcement.getSeq() + "/",
						CommonConstant.ANNOUNCEMENT_IMAGE_PERFIX, announcement.getSeq());
				announcement.setSummaryImage(imageName);
			} catch (Exception e) {
				e.printStackTrace();
			}
			announcementDao.merge(announcement);
		}

		if (!CommonUtil.isEmpty(announcementDTO.getDetailImageFile().getOriginalFilename())) {
			try {
				String imageName = ImagesUtil.uploadMultipartFile(announcementDTO.getDetailImageFile(),
						CommonConstant.ANNOUNCEMENT_DIRECTORY + CommonConstant.ANNOUNCEMENT_IMAGE_DIRECTORY
								+ announcement.getSeq() + "/",
						CommonConstant.ANNOUNCEMENT_IMAGE_PERFIX, announcement.getSeq());
				announcement.setDetailImage(imageName);
			} catch (Exception e) {
				e.printStackTrace();
			}
			announcementDao.merge(announcement);
		}

		// send announcement into active user
		sendFcmTopic(announcement);

		logger.info("manageAnnouncement() >>> Start");
		return new AnnouncementDTO(announcement);
	}

	private void sendFcmTopic(Announcement noti) {
		System.out.println("sendFcmTopic() => start");
		logger.info("sendFcmTopic() => start");
		UserDTO customerDTO = new UserDTO();
		customerDTO.setStatus(CommonStatus.ACTIVE.getCode());
		List<User> activeMobileUserList = userDao.getActiveUserForAnnouncement();
		if (activeMobileUserList != null && !activeMobileUserList.isEmpty()) {
			List<NotificationDTO> notificationList = new ArrayList<>();

			for (User activeUserDTO : activeMobileUserList) {

				if (!CommonUtil.isEmpty(activeUserDTO.getDeviceToken())) {
					System.out.println("sendFcmTopic() => user token is not null");
					NotificationDTO notificationDTO = new NotificationDTO();
					NotificationDataDTO data = new NotificationDataDTO();
					data.setId(noti.getSeq());
					data.setTitle(noti.getTitle());
					data.setBody(noti.getDescription());
					data.setNotificationType(noti.getNotiType());
					data.setNotificationTypeDesc(NotificationTypeEnum.getDescriptionByCode(noti.getNotiType()));

					notificationDTO.setTo(activeUserDTO.getDeviceToken());

					// send notification message to IOS device only
					if (activeUserDTO.getDeviceType() == DeviceTypeEnum.IOS.getCode()) {
						notificationDTO.setNotification(data);
					}
					notificationDTO.setData(data);
					logger.debug("Sent notification >>> Noti Id - " + noti.getSeq() + ", Mobile User Id - "
							+ activeUserDTO.getUserSeq() + ", Mobile User Name - " + activeUserDTO.getUserName());
					notificationList.add(notificationDTO);
				}
			}

			try {
				fcmService.pushFCMNotification(notificationList);
			} catch (Exception e) {
				logger.error("pushFCMNotification failed." + e.getMessage());
				e.printStackTrace();
			}
		}
		logger.info("sendFcmTopic() => finish");
	}

}
