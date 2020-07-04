package com.finder.ecoshop.repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.finder.ecoshop.core.domain.Category;
import com.finder.ecoshop.core.dto.CategoryDTO;
import com.finder.ecoshop.repository.CategoryDao;
import com.finder.ecoshop.utils.CommonStatus;
import com.finder.ecoshop.utils.CommonUtil;
import com.finder.ecoshop.utils.FeatureEnum;

@SuppressWarnings("deprecation")
@Repository
public class CategoryDaoImpl extends GenericDaoImpl<Category, Long> implements CategoryDao{

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getAllCategoryList() {
		Criteria c = this.getCurrentSession().createCriteria(Category.class);
		c.add(Restrictions.eq("status", CommonStatus.ACTIVE.getCode()));
		return c.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> searchCategoryByData(CategoryDTO categoryDTO) {
		Criteria c = this.getCurrentSession().createCriteria(Category.class);
		
		if(!CommonUtil.isEmpty(categoryDTO.getName())) {
			c.add(Restrictions.like("name", categoryDTO.getName(), MatchMode.ANYWHERE));
		}
		
		if(categoryDTO.getSequenceNo() > 0) {
			c.add(Restrictions.eq("sequence", categoryDTO.getSequenceNo()));
		}
		
		if(categoryDTO.getStatus() > 0) {
			c.add(Restrictions.eq("status", categoryDTO.getStatus()));
		}
		
		return c.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getFeatureCategoryList() {
		String sqlStr = "from Category where feature = " + FeatureEnum.FEATURE.getCode() + " order by sequence";
		Query<Category> query = this.getCurrentSession().createQuery(sqlStr);
		return query.list();
	}

}
