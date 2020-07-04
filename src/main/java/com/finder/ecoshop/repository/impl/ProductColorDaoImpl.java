package com.finder.ecoshop.repository.impl;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.finder.ecoshop.core.domain.ProductColor;
import com.finder.ecoshop.repository.ProductColorDao;

@SuppressWarnings("deprecation")
@Repository
public class ProductColorDaoImpl extends GenericDaoImpl<ProductColor, Long> implements ProductColorDao {

	@Override
	public void deleteByProductId(long productId) {
		SQLQuery query = this.getCurrentSession().createSQLQuery("DELETE FROM product_color c WHERE c.product_id = :prdId");
		query.setParameter("prdId", productId);
		query.executeUpdate();
	}

}
