package com.finder.innox.core.dto;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import com.finder.innox.core.domain.Announcement;
import com.finder.innox.utils.CommonStatus;
import com.finder.innox.utils.NotificationTypeEnum;

public class AnnouncementDTO implements Serializable {

	private static final long serialVersionUID = 1083844285708628579L;

	private long seq;

	private Integer notiType;

	private String notiTypeText;

	private String title;

	private String description;

	private String summaryImage;

	private MultipartFile summaryImageFile;

	private String detailImage;

	private MultipartFile detailImageFile;

	private UserDTO userDTO;

	private int status;

	private String statusText;

	public AnnouncementDTO() {
		super();
	}

	public AnnouncementDTO(Announcement announcement) {
		if (announcement != null) {
			this.seq = announcement.getSeq();
			this.notiType = announcement.getNotiType();
			this.notiTypeText = NotificationTypeEnum.getDescriptionByCode(this.notiType);
			this.title = announcement.getTitle();
			this.description = announcement.getDescription();
			this.summaryImage = announcement.getSummaryImage();
			this.detailImage = announcement.getDetailImage();
			this.userDTO = new UserDTO(announcement.getCreatedBy());
			this.status = announcement.getStatus() == null ? CommonStatus.ACTIVE.getCode() : announcement.getStatus();
			this.statusText = CommonStatus.getDescByCode(this.status);
		}
	}

	public long getSeq() {
		return seq;
	}

	public void setSeq(long seq) {
		this.seq = seq;
	}

	public Integer getNotiType() {
		return notiType;
	}

	public void setNotiType(Integer notiType) {
		this.notiType = notiType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSummaryImage() {
		return summaryImage;
	}

	public void setSummaryImage(String summaryImage) {
		this.summaryImage = summaryImage;
	}

	public String getDetailImage() {
		return detailImage;
	}

	public void setDetailImage(String detailImage) {
		this.detailImage = detailImage;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public MultipartFile getSummaryImageFile() {
		return summaryImageFile;
	}

	public void setSummaryImageFile(MultipartFile summaryImageFile) {
		this.summaryImageFile = summaryImageFile;
	}

	public MultipartFile getDetailImageFile() {
		return detailImageFile;
	}

	public void setDetailImageFile(MultipartFile detailImageFile) {
		this.detailImageFile = detailImageFile;
	}

	public String getNotiTypeText() {
		return notiTypeText;
	}

	public void setNotiTypeText(String notiTypeText) {
		this.notiTypeText = notiTypeText;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getStatusText() {
		return statusText;
	}

	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}

}
