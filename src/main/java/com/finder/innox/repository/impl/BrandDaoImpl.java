package com.finder.innox.repository.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.finder.innox.core.domain.Brand;
import com.finder.innox.repository.BrandDao;

@Repository
public class BrandDaoImpl extends GenericDaoImpl<Brand, Long> implements BrandDao{

	@Override
	public List<Brand> getAllBrandList() {
		CriteriaBuilder cb = this.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Brand> cr = cb.createQuery(Brand.class);
		Root<Brand> root  = cr.from(Brand.class);
//		cr.select(root)
//			.where(cb.equal(root.get("status"), CommonStatus.ACTIVE.getCode()));
		
		Query<Brand> query = this.getCurrentSession().createQuery(cr);
		List<Brand> entityList = query.getResultList();
		return entityList;
	}

}
