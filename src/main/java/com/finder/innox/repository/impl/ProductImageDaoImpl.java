package com.finder.innox.repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.finder.innox.core.domain.ProductImage;
import com.finder.innox.repository.ProductImageDao;

@SuppressWarnings("deprecation")
@Repository
public class ProductImageDaoImpl extends GenericDaoImpl<ProductImage, Long> implements ProductImageDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductImage> getProductImageListByPrdId(long prdId) {
		Criteria c = this.getCurrentSession().createCriteria(ProductImage.class);
		c.add(Restrictions.eq("product.seq", prdId));
		return c.list();
	}

}
