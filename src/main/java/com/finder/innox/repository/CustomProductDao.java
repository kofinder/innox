package com.finder.innox.repository;

import java.util.List;

import com.finder.innox.core.domain.CustomProduct;
import com.finder.innox.core.dto.CustomProductDTO;

public interface CustomProductDao extends GenericDao<CustomProduct, Long> {

	List<CustomProduct> searchCustomProduct(CustomProductDTO searchCusPrdDTO);

	List<CustomProduct> getCustomProductListBySubCat(long subCategoryId);
	
	CustomProduct getCustomProductById(long customProductId, int status);

}
