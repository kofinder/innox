package com.finder.innox.repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.finder.innox.core.domain.Size;
import com.finder.innox.repository.SizeDao;

@SuppressWarnings("deprecation")
@Repository
public class SizeDaoImpl extends GenericDaoImpl<Size, Long> implements SizeDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Size> getAllSize(int status) {
		Criteria c = this.getCurrentSession().createCriteria(Size.class);
		if (status > 0) {
			c.add(Restrictions.eq("status", status));
		}
		return c.list();
	}

}
