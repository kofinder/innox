package com.finder.innox.repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.finder.innox.core.domain.CustomItemLayout;
import com.finder.innox.repository.CustomerItemLayoutDao;

@SuppressWarnings("deprecation")
@Repository
public class CustomItemLayoutDaoImpl extends GenericDaoImpl<CustomItemLayout, Long> implements CustomerItemLayoutDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomItemLayout> getCustomItemLayoutListByItemId(long customItemId, int status) {
		Criteria c = this.getCurrentSession().createCriteria(CustomItemLayout.class);
		c.add(Restrictions.eq("customItem.seq", customItemId));
		if (status > 0) {
			c.add(Restrictions.eq("statue", status));
		}

		c.addOrder(Order.asc("sequenceNo"));
		return c.list();
	}

	@Override
	public int isValidLayoutName(long customItemId, String layoutName, long seq) {
		Criteria c = this.getCurrentSession().createCriteria(CustomItemLayout.class);
		c.add(Restrictions.eq("customItem.id", customItemId));
		c.add(Restrictions.eq("layoutName", layoutName));
		if (seq > 0) {
			c.add(Restrictions.ne("id", seq));
		}
		return c.uniqueResult() == null ? 1 : 0;
	}

}
