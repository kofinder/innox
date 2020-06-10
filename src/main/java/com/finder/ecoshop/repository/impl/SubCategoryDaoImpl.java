package com.finder.ecoshop.repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.finder.ecoshop.core.domain.SubCategory;
import com.finder.ecoshop.repository.SubCategoryDao;

@SuppressWarnings("deprecation")
@Repository
public class SubCategoryDaoImpl extends GenericDaoImpl<SubCategory, Long> implements SubCategoryDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<SubCategory> getAllSubCategoryList() {
		Criteria c = this.getCurrentSession().createCriteria(SubCategory.class);
		return c.list();
	}

}
