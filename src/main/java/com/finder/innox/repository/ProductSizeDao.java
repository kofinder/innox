package com.finder.innox.repository;

import com.finder.innox.core.domain.ProductSize;

public interface ProductSizeDao extends GenericDao<ProductSize, Long>{
	
	void deleteByProductId(long prdId);
}
