package com.finder.innox.repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.finder.innox.core.domain.Township;
import com.finder.innox.repository.TownshipDao;

@SuppressWarnings({ "unchecked", "deprecation" })
@Repository
public class TownshipDaoImpl extends GenericDaoImpl<Township, Long> implements TownshipDao {

	@Override
	public List<Township> searchTownshipList() {
		Criteria c = this.getCurrentSession().createCriteria(Township.class);
		c.addOrder(Order.asc("townshipName"));
		return c.list();
	}
}
