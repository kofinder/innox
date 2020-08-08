package com.finder.innox.repository;

import java.util.List;

import com.finder.innox.core.domain.Category;
import com.finder.innox.core.dto.CategoryDTO;

public interface CategoryDao extends GenericDao<Category, Long>{
	
	List<Category> getAllCategoryList();
	
	List<Category> searchCategoryByData(CategoryDTO categoryDTO);
	
	List<Category> getFeatureCategoryList();

}
