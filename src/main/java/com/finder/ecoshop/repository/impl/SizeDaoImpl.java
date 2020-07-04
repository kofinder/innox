package com.finder.ecoshop.repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.finder.ecoshop.core.domain.Size;
import com.finder.ecoshop.repository.SizeDao;

@SuppressWarnings("deprecation")
@Repository
public class SizeDaoImpl extends GenericDaoImpl<Size, Long> implements SizeDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Size> getAllSize() {
		Criteria c = this.getCurrentSession().createCriteria(Size.class);
		return c.list();
	}

}
