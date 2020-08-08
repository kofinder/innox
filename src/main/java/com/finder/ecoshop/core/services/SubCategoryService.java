package com.finder.ecoshop.core.services;

import java.util.List;

import com.finder.ecoshop.core.dto.SubCategoryDTO;

public interface SubCategoryService {
	
	List<SubCategoryDTO> getAllSubCategoryListByCatId(Long catId, int status);
	
	SubCategoryDTO getSubCategoryById(Long subCatId);
	
	SubCategoryDTO subCategoryManage(SubCategoryDTO subCategoryDTO);
	
	List<SubCategoryDTO> getAllActiveSubCategoryList();

}
