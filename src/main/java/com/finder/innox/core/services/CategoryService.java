package com.finder.innox.core.services;

import java.util.List;

import com.finder.innox.core.dto.CategoryDTO;

public interface CategoryService {
	
	List<CategoryDTO> getAllCategoryList();
	
	CategoryDTO getCategoryById(Long catId);
	
	CategoryDTO manageCategory(CategoryDTO categoryDTO);
	
	List<CategoryDTO> searchCategoryByData(CategoryDTO categoryDTO);
	
	List<CategoryDTO> getFeatureCategoryList();
}
