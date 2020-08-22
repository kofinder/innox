package com.finder.innox.repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.finder.innox.core.domain.CustomItem;
import com.finder.innox.repository.CustomItemDao;

@SuppressWarnings("deprecation")
@Repository
public class CustomItemDaoImpl extends GenericDaoImpl<CustomItem, Long> implements CustomItemDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomItem> getCustomItemListByCustomProductId(long id, int status) {
		Criteria c = this.getCurrentSession().createCriteria(CustomItem.class);
		c.add(Restrictions.eq("customProduct.seq", id));
		c.addOrder(Order.asc("sequenceNo"));
		return c.list();
	}

}
