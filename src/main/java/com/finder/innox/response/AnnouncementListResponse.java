package com.finder.innox.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AnnouncementListResponse implements Serializable {

	private static final long serialVersionUID = 3644935924366754469L;

	private List<AnnouncementResponse> announcements = new ArrayList<AnnouncementResponse>();

	public List<AnnouncementResponse> getAnnouncements() {
		return announcements;
	}

	public void setAnnouncements(List<AnnouncementResponse> announcements) {
		this.announcements = announcements;
	}

}
