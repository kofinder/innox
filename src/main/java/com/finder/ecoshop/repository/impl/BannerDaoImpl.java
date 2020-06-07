package com.finder.ecoshop.repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.finder.ecoshop.core.domain.Banner;
import com.finder.ecoshop.repository.BannerDao;

@SuppressWarnings("deprecation")
@Repository
public class BannerDaoImpl extends GenericDaoImpl<Banner, Long> implements BannerDao {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Banner> getAllBannerList() {
		Criteria c = this.sessionFactory.getCurrentSession().createCriteria(Banner.class);
		c.add(Restrictions.eq("status", 1));
		return c.list();
	}

}
