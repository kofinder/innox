package com.finder.ecoshop.repository;

import java.util.List;

import com.finder.ecoshop.core.domain.Category;
import com.finder.ecoshop.core.dto.CategoryDTO;

public interface CategoryDao extends GenericDao<Category, Long>{
	
	List<Category> getAllCategoryList();
	
	List<Category> searchCategoryByData(CategoryDTO categoryDTO);
	
	List<Category> getFeatureCategoryList();

}
