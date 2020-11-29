package com.finder.innox.repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
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
		c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return c.list();
	}

	@Override
	public List<Township> getTownshipListByState(Long stateId) {
		Criteria c = this.getCurrentSession().createCriteria(Township.class);
		if (stateId != null && stateId > 0) {
			c.add(Restrictions.eq("state.seq", stateId));
		}
		c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return c.list();
	}
}
