package com.finder.innox.response;

import java.io.Serializable;

public class AnnouncementResponse implements Serializable {

	private static final long serialVersionUID = 815531510738958509L;

	private long announcement_id;

	private int noti_type;

	private String noti_type_text;

	private String title;

	private String description;

	private String created_date;

	private String summary_image;

	private String detail_image;

	public long getAnnouncement_id() {
		return announcement_id;
	}

	public void setAnnouncement_id(long announcement_id) {
		this.announcement_id = announcement_id;
	}

	public int getNoti_type() {
		return noti_type;
	}

	public void setNoti_type(int noti_type) {
		this.noti_type = noti_type;
	}

	public String getNoti_type_text() {
		return noti_type_text;
	}

	public void setNoti_type_text(String noti_type_text) {
		this.noti_type_text = noti_type_text;
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

	public String getSummary_image() {
		return summary_image;
	}

	public void setSummary_image(String summary_image) {
		this.summary_image = summary_image;
	}

	public String getDetail_image() {
		return detail_image;
	}

	public void setDetail_image(String detail_image) {
		this.detail_image = detail_image;
	}

	public String getCreated_date() {
		return created_date;
	}

	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}

}
