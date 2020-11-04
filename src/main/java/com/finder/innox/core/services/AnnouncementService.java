package com.finder.innox.core.services;

import java.util.List;

import com.finder.innox.core.dto.AnnouncementDTO;

public interface AnnouncementService {

	AnnouncementDTO getDataById(long announcementId);

	AnnouncementDTO manageAnnouncement(AnnouncementDTO announcementDTO) throws Exception;

	List<AnnouncementDTO> searchAnnouncementData(AnnouncementDTO announcementDTO);

}
