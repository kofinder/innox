package com.finder.innox.repository.impl;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.finder.innox.core.domain.ProductSize;
import com.finder.innox.repository.ProductSizeDao;

@SuppressWarnings({ "unchecked", "deprecation" })
@Repository
public class ProductSizeDaoImpl extends GenericDaoImpl<ProductSize, Long> implements ProductSizeDao {

	@Override
	public void deleteByProductId(long prdId) {
		String sqlStr = "DELETE FROM product_size WHERE product_id = " + prdId;
		SQLQuery<ProductSize> query = this.getCurrentSession().createSQLQuery(sqlStr);
		query.executeUpdate();
	}

}
