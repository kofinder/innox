package com.finder.innox.core.services;

import java.math.BigDecimal;
import java.util.List;

import com.finder.innox.core.dto.ProductDTO;

public interface ProductService {

	ProductDTO manageProduct(ProductDTO productDTO);

	List<ProductDTO> productSearch(ProductDTO searchProductDTO);

	ProductDTO getProductDataById(Long prdId);

	List<ProductDTO> getPopularProductList();

	List<ProductDTO> getProductListByPageNo(int pageNo);

	List<ProductDTO> getPromotionProductList();

	List<ProductDTO> getProductListBySubCatgory(long subCategoryId, int pageNo);

	List<ProductDTO> searchProductList(String keyword, BigDecimal startPrice, BigDecimal endPrice, Long category,
			Long subCategory);
}
