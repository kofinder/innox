package com.finder.innox.repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.finder.innox.core.domain.ArtworkCategory;
import com.finder.innox.repository.ArtworkCategoryDao;
import com.finder.innox.utils.CommonStatus;

@SuppressWarnings({ "unchecked", "deprecation" })
@Repository
public class ArtworkCategoryDaoImpl extends GenericDaoImpl<ArtworkCategory, Long> implements ArtworkCategoryDao {

	@Override
	public List<ArtworkCategory> getArtworkCategoryList() {
		Criteria c = this.getCurrentSession().createCriteria(ArtworkCategory.class);
		c.add(Restrictions.eq("status", CommonStatus.ACTIVE.getCode()));
		c.addOrder(Order.asc("categoryName"));
		return c.list();
	}

}
