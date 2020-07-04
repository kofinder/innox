package com.finder.ecoshop.repository;

import com.finder.ecoshop.core.domain.ProductColor;

public interface ProductColorDao extends GenericDao<ProductColor, Long>{
	
	void deleteByProductId(long productId);

}
