package com.finder.innox.repository.impl;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.finder.innox.core.domain.ProductColor;
import com.finder.innox.repository.ProductColorDao;

@SuppressWarnings({ "unchecked", "deprecation" })
@Repository
public class ProductColorDaoImpl extends GenericDaoImpl<ProductColor, Long> implements ProductColorDao {

	@Override
	public void deleteByProductId(long productId) {
		String sqlStr = "DELETE FROM product_color WHERE product_id = " + productId;
		SQLQuery<ProductColor> query = this.getCurrentSession().createSQLQuery(sqlStr);
		query.executeUpdate();
	}

}
