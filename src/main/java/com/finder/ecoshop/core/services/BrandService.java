package com.finder.ecoshop.core.services;

import java.util.List;

import com.finder.ecoshop.core.dto.BrandDTO;

public interface BrandService {
	
	List<BrandDTO> getAllBrandList();
	
	BrandDTO saveBrand(BrandDTO brandDTO) throws Exception;
	
	BrandDTO getBrandById(Long brandId);

}
