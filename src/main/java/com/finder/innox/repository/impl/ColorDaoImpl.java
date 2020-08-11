package com.finder.innox.repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.finder.innox.core.domain.Color;
import com.finder.innox.repository.ColorDao;
import com.finder.innox.utils.CommonStatus;

@SuppressWarnings({ "unchecked", "deprecation" })
@Repository
public class ColorDaoImpl extends GenericDaoImpl<Color, Long> implements ColorDao {

	@Override
	public List<Color> getAllColorList(int status) {
		Criteria c = this.getCurrentSession().createCriteria(Color.class);
		if (status > 0) {
			c.add(Restrictions.eq("status", CommonStatus.ACTIVE.getCode()));
		}
		c.addOrder(Order.asc("status"));
		return c.list();
	}

}
