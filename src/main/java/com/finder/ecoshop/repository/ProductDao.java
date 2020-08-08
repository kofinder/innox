package com.finder.ecoshop.repository;

import java.math.BigDecimal;
import java.util.List;

import com.finder.ecoshop.core.domain.Product;
import com.finder.ecoshop.core.dto.ProductDTO;

public interface ProductDao extends GenericDao<Product, Long> {

	List<Product> productSearch(ProductDTO searchProductDTO);

	List<Product> getPopularProductList();

	List<Product> getProductListByPageNo(int pageNo);

	List<Product> getPromotionProductList();

	List<Product> getProductListBySubCatgory(long subCategoryId);

	List<Product> searchProductList(String keyword, BigDecimal startPrice, BigDecimal endPrice, Long category,
			Long subCategory);

}
