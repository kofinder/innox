package com.finder.ecoshop.repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.finder.ecoshop.core.domain.CustomItemLayout;
import com.finder.ecoshop.repository.CustomerItemLayoutDao;

@SuppressWarnings("deprecation")
@Repository
public class CustomItemLayoutDaoImpl extends GenericDaoImpl<CustomItemLayout, Long> implements CustomerItemLayoutDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomItemLayout> getCustomItemLayoutListByItemId(long customItemId) {
		Criteria c = this.getCurrentSession().createCriteria(CustomItemLayout.class);
		c.add(Restrictions.eq("customItem.seq", customItemId));
		return c.list();
	}

	@Override
	public int isValidLayoutName(long customItemId, String layoutName, long seq) {
		Criteria c = this.getCurrentSession().createCriteria(CustomItemLayout.class);
		c.add(Restrictions.eq("customItem.id", customItemId));
		c.add(Restrictions.eq("layoutName", layoutName));
		if(seq > 0) {
			c.add(Restrictions.ne("id", seq));
		}
		return c.uniqueResult() == null ? 1 : 0;
	}

}
