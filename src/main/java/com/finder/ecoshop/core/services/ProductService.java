package com.finder.ecoshop.core.services;

import java.util.List;

import com.finder.ecoshop.core.dto.ProductDTO;

public interface ProductService {
	
	ProductDTO manageProduct(ProductDTO productDTO);
	
	List<ProductDTO> productSearch(ProductDTO searchProductDTO);
	
	ProductDTO getProductDataById(Long prdId);
	
	List<ProductDTO> getPopularProductList();
	
	List<ProductDTO> getProductListByPageNo(int pageNo);
}
