package com.finder.innox.repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.finder.innox.core.domain.SubCategory;
import com.finder.innox.repository.SubCategoryDao;
import com.finder.innox.utils.CommonStatus;

@SuppressWarnings("deprecation")
@Repository
public class SubCategoryDaoImpl extends GenericDaoImpl<SubCategory, Long> implements SubCategoryDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<SubCategory> getAllSubCategoryListByCatId(Long catId, int status) {
		Criteria c = this.getCurrentSession().createCriteria(SubCategory.class);
		c.add(Restrictions.eq("category.id", catId));
		
		if(status > 0) {
			c.add(Restrictions.eq("status", status));
		}
		return c.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SubCategory> getAllActiveSubCategoryList() {
		Criteria c = this.getCurrentSession().createCriteria(SubCategory.class);
		c.add(Restrictions.eq("status", CommonStatus.ACTIVE.getCode()));
		return c.list();
	}

}
