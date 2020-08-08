package com.finder.innox.repository;

import com.finder.innox.core.domain.ProductColor;

public interface ProductColorDao extends GenericDao<ProductColor, Long>{
	
	void deleteByProductId(long productId);

}
