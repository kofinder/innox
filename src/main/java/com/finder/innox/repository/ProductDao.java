package com.finder.innox.repository;

import java.math.BigDecimal;
import java.util.List;

import com.finder.innox.core.domain.Product;
import com.finder.innox.core.dto.ProductDTO;

public interface ProductDao extends GenericDao<Product, Long> {

	List<Product> productSearch(ProductDTO searchProductDTO);

	List<Product> getPopularProductList();

	List<Product> getProductListByPageNo(int pageNo);

	List<Product> getPromotionProductList();

	List<Product> getProductListBySubCatgory(long subCategoryId, int pageNo);

	List<Product> searchProductList(String keyword, BigDecimal startPrice, BigDecimal endPrice, Long category,
			Long subCategory);

	boolean reduceItemQty(Long productId, int quantity);
	
	boolean addItemQty(Long productId, int quantity);

}
