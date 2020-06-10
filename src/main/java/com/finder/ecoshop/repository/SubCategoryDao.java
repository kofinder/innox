package com.finder.ecoshop.repository;

import java.util.List;

import com.finder.ecoshop.core.domain.SubCategory;

public interface SubCategoryDao extends GenericDao<SubCategory, Long>{
	
	List<SubCategory> getAllSubCategoryList();
}
