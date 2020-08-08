package com.finder.innox.repository;

import java.util.List;

import com.finder.innox.core.domain.SubCategory;

public interface SubCategoryDao extends GenericDao<SubCategory, Long>{
	
	List<SubCategory> getAllSubCategoryListByCatId(Long catId, int status);
	
	List<SubCategory> getAllActiveSubCategoryList();
}
