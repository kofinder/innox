package com.finder.ecoshop.repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.finder.ecoshop.core.domain.CustomItem;
import com.finder.ecoshop.repository.CustomItemDao;

@SuppressWarnings("deprecation")
@Repository
public class CustomItemDaoImpl extends GenericDaoImpl<CustomItem, Long> implements CustomItemDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomItem> getCustomItemListByCustomProductId(long id) {
		Criteria c = this.getCurrentSession().createCriteria(CustomItem.class);
		c.add(Restrictions.eq("customProduct.seq", id));
		return c.list();
	}

}
