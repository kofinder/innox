package com.finder.ecoshop.core.services;

import java.util.List;

import com.finder.ecoshop.core.dto.SubCategoryDTO;

public interface SubCategoryService {
	
	List<SubCategoryDTO> getAllSubCategoryList();
	
	SubCategoryDTO getSubCategoryById(Long subCatId);

}
