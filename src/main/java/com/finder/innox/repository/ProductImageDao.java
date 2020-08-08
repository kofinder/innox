package com.finder.innox.repository;

import java.util.List;

import com.finder.innox.core.domain.ProductImage;

public interface ProductImageDao extends GenericDao<ProductImage, Long>{
	
	List<ProductImage> getProductImageListByPrdId(long prdId);

}
