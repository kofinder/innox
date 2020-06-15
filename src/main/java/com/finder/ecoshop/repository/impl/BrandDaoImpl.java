package com.finder.ecoshop.repository.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.finder.ecoshop.core.domain.Brand;
import com.finder.ecoshop.repository.BrandDao;
import com.finder.ecoshop.utils.CommonStatus;

@Repository
public class BrandDaoImpl extends GenericDaoImpl<Brand, Long> implements BrandDao{

	@Override
	public List<Brand> getAllBrandList() {
		CriteriaBuilder cb = this.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Brand> cr = cb.createQuery(Brand.class);
		Root<Brand> root  = cr.from(Brand.class);
		cr.select(root)
			.where(cb.equal(root.get("status"), CommonStatus.ACTIVE.getCode()));
		
		Query<Brand> query = this.getCurrentSession().createQuery(cr);
		List<Brand> entityList = query.getResultList();
		return entityList;
	}

}
