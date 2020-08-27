package com.finder.innox.repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.finder.innox.core.domain.Zone;
import com.finder.innox.repository.ZoneDao;

@SuppressWarnings({ "unchecked", "deprecation" })
@Repository
public class ZoneDaoImpl extends GenericDaoImpl<Zone, Long> implements ZoneDao {

	@Override
	public List<Zone> getAllZoneList() {
		Criteria c = this.getCurrentSession().createCriteria(Zone.class);
		return c.list();
	}

}
