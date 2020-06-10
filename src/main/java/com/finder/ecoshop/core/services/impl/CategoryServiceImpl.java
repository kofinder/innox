package com.finder.ecoshop.core.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finder.ecoshop.core.domain.Category;
import com.finder.ecoshop.core.dto.CategoryDTO;
import com.finder.ecoshop.core.services.CategoryService;
import com.finder.ecoshop.repository.CategoryDao;
import com.finder.ecoshop.utils.CommonConstant;
import com.finder.ecoshop.utils.CommonStatus;
import com.finder.ecoshop.utils.CommonUtil;
import com.finder.ecoshop.utils.ImagesUtil;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{
	
	private final Logger logger = LogManager.getLogger(this.getClass());
	
	@Autowired
	private CategoryDao categoryDao;

	@Override
	public List<CategoryDTO> getAllCategoryList() {
		List<Category> categoryList = categoryDao.getAllCategoryList();
		if(categoryList == null || categoryList.isEmpty()) {
			return new ArrayList<CategoryDTO>();
		}
		
		List<CategoryDTO> dtoList = new ArrayList<CategoryDTO>();
		categoryList.forEach(cat -> {
			dtoList.add(new CategoryDTO(cat));
		});
		
		logger.info("getAllCategoryList() >> List Size : " + dtoList.size());
		return dtoList;
	}

	@Override
	public CategoryDTO getCategoryById(Long catId) {
		logger.info("getCategoryById() >> Cat Id : " + catId);
		Category category = categoryDao.get(catId);
		if(category != null) {
			return new CategoryDTO(category);
		}
		
		logger.info("getCategoryById() >> Category : NULL");
		return null;
	}

	@Override
	public CategoryDTO manageCategory(CategoryDTO categoryDTO) {
		Category category = null;
		if(categoryDTO.getSeq() > 0) {
			category = categoryDao.get(categoryDTO.getSeq());
			category.setName(categoryDTO.getName());
			category.setSequence(categoryDTO.getSequenceNo());
			category.setStatus(categoryDTO.getStatus());
			category.setUpdatedTime(new Date());
			
			categoryDao.update(category);
		}else {
			category = new Category();
			category.setName(categoryDTO.getName());
			category.setSequence(categoryDTO.getSequenceNo());
			category.setStatus(CommonStatus.ACTIVE.getCode());
			category.setCreatedTime(new Date());
			
			categoryDao.save(category);
		}
		
		if(!CommonUtil.isEmpty(categoryDTO.getImageFile().getOriginalFilename())) {
			try {
				String imageName = ImagesUtil.uploadMultipartFile(categoryDTO.getImageFile(), 
						CommonConstant.CATEGORY_DIRECTORY + CommonConstant.CATEGORY_IMAGE_DIRECTORY + category.getSeq() + "/", 
						CommonConstant.CATEGORY_IMAGE_PERFIX, category.getSeq());
				category.setImagePath(imageName);
			} catch (Exception e) {
				e.printStackTrace();
			}
			categoryDao.merge(category);
		}
		
		logger.info("manageCategory() >> End >> Cateogry id : " + category.getSeq());
		return new CategoryDTO(categoryDao.get(category.getSeq()));
	}

}
