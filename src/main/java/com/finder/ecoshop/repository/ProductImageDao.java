package com.finder.ecoshop.repository;

import java.util.List;

import com.finder.ecoshop.core.domain.ProductImage;

public interface ProductImageDao extends GenericDao<ProductImage, Long>{
	
	List<ProductImage> getProductImageListByPrdId(long prdId);

}
