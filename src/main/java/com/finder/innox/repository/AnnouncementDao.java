package com.finder.innox.repository;

import java.util.List;

import com.finder.innox.core.domain.Announcement;
import com.finder.innox.core.dto.AnnouncementDTO;

public interface AnnouncementDao extends GenericDao<Announcement, Long> {

	List<Announcement> searchAnnouncementData(AnnouncementDTO announcementDTO);

}
