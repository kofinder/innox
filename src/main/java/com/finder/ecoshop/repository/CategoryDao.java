package com.finder.ecoshop.repository;

import java.util.List;

import com.finder.ecoshop.core.domain.Category;

public interface CategoryDao extends GenericDao<Category, Long>{
	
	List<Category> getAllCategoryList();

}
