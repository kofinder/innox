package com.finder.innox.repository.impl;

import org.springframework.stereotype.Repository;

import com.finder.innox.core.domain.Announcement;
import com.finder.innox.repository.AnnouncementDao;

@Repository
public class AnnouncementDaoImpl extends GenericDaoImpl<Announcement, Long> implements AnnouncementDao {

}
