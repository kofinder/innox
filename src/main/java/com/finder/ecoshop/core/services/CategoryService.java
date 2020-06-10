package com.finder.ecoshop.core.services;

import java.util.List;

import com.finder.ecoshop.core.dto.CategoryDTO;

public interface CategoryService {
	
	List<CategoryDTO> getAllCategoryList();
	
	CategoryDTO getCategoryById(Long catId);
	
	CategoryDTO manageCategory(CategoryDTO categoryDTO);
}
