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
import com.finder.ecoshop.core.domain.SubCategory;
import com.finder.ecoshop.core.dto.SubCategoryDTO;
import com.finder.ecoshop.core.services.SubCategoryService;
import com.finder.ecoshop.repository.SubCategoryDao;
import com.finder.ecoshop.utils.CommonConstant;
import com.finder.ecoshop.utils.CommonUtil;
import com.finder.ecoshop.utils.ImagesUtil;

@Service
@Transactional
public class SubCategoryServiceImpl implements SubCategoryService {

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private SubCategoryDao subCategoryDao;

	@Override
	public List<SubCategoryDTO> getAllSubCategoryListByCatId(Long catId) {
		logger.info("getAllSubCategoryListByCatId() >> " + catId);
		List<SubCategory> entityList = subCategoryDao.getAllSubCategoryListByCatId(catId);

		if (entityList == null || entityList.isEmpty()) {
			logger.info("getAllSubCategoryListByCatId() >> NULL");
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
		if (subCategory != null) {
			return new SubCategoryDTO(subCategory);
		}
		logger.info("getSubCategoryById() >> NULL");
		return null;
	}

	@Override
	public SubCategoryDTO subCategoryManage(SubCategoryDTO subCategoryDTO) {
		logger.info("subCategoryManage() >> Start");
		SubCategory subCategory = null;

		if (subCategoryDTO.getSeq() > 0) {
			// update
			subCategory = subCategoryDao.get(subCategoryDTO.getSeq());
			subCategory.setName(subCategoryDTO.getName());
			subCategory.setStatus(subCategoryDTO.getStatus());
			subCategory.setSequence(subCategoryDTO.getSequenceNo());
			subCategory.setUpdatedTime(new Date());

			subCategoryDao.update(subCategory);
			logger.info("subCategoryManage() >> Update successful");
		} else {
			// save
			subCategory = new SubCategory();
			subCategory.setName(subCategoryDTO.getName());
			subCategory.setStatus(subCategoryDTO.getStatus());
			subCategory.setSequence(subCategoryDTO.getSequenceNo());

			Category category = new Category();
			category.setSeq(subCategoryDTO.getCategorySeq());
			subCategory.setCategory(category);

			subCategory.setCreatedTime(new Date());

			subCategoryDao.save(subCategory);
			logger.info("subCategoryManage() >> Save successful");
		}

		if (!CommonUtil.isEmpty(subCategoryDTO.getImageFile().getOriginalFilename())) {
			try {
				String imageName = ImagesUtil.uploadMultipartFile(subCategoryDTO.getImageFile(),
						CommonConstant.SUB_CATEGORY_DIRECTORY + CommonConstant.SUB_CATEGORY_IMAGE_DIRECTORY
								+ subCategory.getSeq() + "/",
						CommonConstant.SUB_CATEGORY_IMAGE_PERFIX, subCategory.getSeq());
				subCategory.setImagePath(imageName);
			} catch (Exception e) {
				e.printStackTrace();
			}
			subCategoryDao.merge(subCategory);
		}

		logger.info("subCategoryManage() >> End >> Sub Cateogry id : " + subCategory.getSeq());
		return new SubCategoryDTO(subCategoryDao.get(subCategory.getSeq()));
	}

	@Override
	public List<SubCategoryDTO> getAllActiveSubCategoryList() {
		List<SubCategory> entityList = subCategoryDao.getAllActiveSubCategoryList();
		if (entityList == null || entityList.isEmpty()) {
			return new ArrayList<SubCategoryDTO>();
		}

		List<SubCategoryDTO> dtoList = new ArrayList<SubCategoryDTO>();
		entityList.forEach(entity -> {
			dtoList.add(new SubCategoryDTO(entity));
		});
		return dtoList;
	}

}
