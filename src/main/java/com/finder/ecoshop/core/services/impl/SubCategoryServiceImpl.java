package com.finder.ecoshop.core.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finder.ecoshop.core.domain.SubCategory;
import com.finder.ecoshop.core.dto.SubCategoryDTO;
import com.finder.ecoshop.core.services.SubCategoryService;
import com.finder.ecoshop.repository.SubCategoryDao;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {
	
	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private SubCategoryDao subCategoryDao;

	@Override
	public List<SubCategoryDTO> getAllSubCategoryList() {
		List<SubCategory> entityList = subCategoryDao.getAllSubCategoryList();

		if (entityList == null || entityList.isEmpty()) {
			return new ArrayList<SubCategoryDTO>();
		}

		List<SubCategoryDTO> dtoList = new ArrayList<>();
		entityList.forEach(sub -> {
			dtoList.add(new SubCategoryDTO(sub));
		});

		return dtoList;
	}

	@Override
	public SubCategoryDTO getSubCategoryById(Long subCatId) {
		logger.info("getSubCategoryById() >> " + subCatId);
		SubCategory subCategory = subCategoryDao.get(subCatId);
		if(subCategory != null) {
			return new SubCategoryDTO(subCategory);
		}
		logger.info("getSubCategoryById() >> NULL");
		return null;
	}

}
