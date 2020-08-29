package com.finder.innox.repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.finder.innox.core.domain.Fonts;
import com.finder.innox.repository.FontsDao;

@SuppressWarnings({ "unchecked", "deprecation" })
@Repository
public class FontsDaoImpl extends GenericDaoImpl<Fonts, Long> implements FontsDao {

	@Override
	public List<Fonts> getFontList(int status) {
		Criteria c = this.getCurrentSession().createCriteria(Fonts.class);
		if (status > 0) {
			c.add(Restrictions.eq("status", status));
		}
		return c.list();
	}

}
