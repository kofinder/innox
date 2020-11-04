package com.finder.innox.repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.finder.innox.core.domain.Announcement;
import com.finder.innox.core.dto.AnnouncementDTO;
import com.finder.innox.repository.AnnouncementDao;
import com.finder.innox.utils.CommonUtil;

@SuppressWarnings({ "unchecked", "deprecation" })
@Repository
public class AnnouncementDaoImpl extends GenericDaoImpl<Announcement, Long> implements AnnouncementDao {

	@Override
	public List<Announcement> searchAnnouncementData(AnnouncementDTO announcementDTO) {
		Criteria c = this.getCurrentSession().createCriteria(Announcement.class);

		if (!CommonUtil.isEmpty(announcementDTO.getTitle())) {
			c.add(Restrictions.ilike("title", announcementDTO.getTitle(), MatchMode.ANYWHERE));
		}

		if (announcementDTO.getStatus() > 0) {
			c.add(Restrictions.eq("status", announcementDTO.getStatus()));
		}

		if (announcementDTO.getNotiType() != null && announcementDTO.getNotiType() > 0) {
			c.add(Restrictions.eq("notiType", announcementDTO.getNotiType()));
		}

		c.addOrder(Order.desc("createdTime"));
		return c.list();
	}

}
