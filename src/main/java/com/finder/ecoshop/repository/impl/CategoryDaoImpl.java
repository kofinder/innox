package com.finder.ecoshop.repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.finder.ecoshop.core.domain.Category;
import com.finder.ecoshop.repository.CategoryDao;

@SuppressWarnings("deprecation")
@Repository
public class CategoryDaoImpl extends GenericDaoImpl<Category, Long> implements CategoryDao{

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getAllCategoryList() {
		Criteria c = this.getCurrentSession().createCriteria(Category.class);
		return c.list();
	}

}
