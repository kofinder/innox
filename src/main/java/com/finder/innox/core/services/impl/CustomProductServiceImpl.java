package com.finder.innox.core.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finder.innox.core.domain.Category;
import com.finder.innox.core.domain.CustomProduct;
import com.finder.innox.core.domain.SubCategory;
import com.finder.innox.core.domain.User;
import com.finder.innox.core.dto.CustomProductDTO;
import com.finder.innox.core.services.CustomProductService;
import com.finder.innox.repository.CustomProductDao;
import com.finder.innox.repository.UserDao;
import com.finder.innox.utils.CommonConstant;
import com.finder.innox.utils.CommonStatus;
import com.finder.innox.utils.CommonUtil;
import com.finder.innox.utils.ImagesUtil;

@Service
@Transactional
public class CustomProductServiceImpl implements CustomProductService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserDao userDao;

	@Autowired
	private CustomProductDao customProductDao;

	@Override
	public CustomProductDTO manageCustomProduct(CustomProductDTO customProductDTO, String loginUserName) throws Exception {
		// get login user info
		User createdBy = userDao.findByUserName(loginUserName);

		// manage custom product
		CustomProduct customProduct = null;
		if (customProductDTO.getSeq() <= 0) {
			// save
			customProduct = new CustomProduct();
			customProduct.setStatus(CommonStatus.ACTIVE.getCode());
			customProduct.setCreatedTime(new Date());
			customProduct.setUpdatedTime(new Date());
		} else {
			// update
			customProduct = customProductDao.get(customProductDTO.getSeq());
			customProduct.setStatus(customProductDTO.getStatus());
			customProduct.setUpdatedTime(new Date());
		}

		if (createdBy != null) {
			customProduct.setUser(createdBy);
		}

		Category category = new Category();
		category.setSeq(customProductDTO.getCategoryDTO().getSeq());
		customProduct.setCategory(category);

		SubCategory subCategory = new SubCategory();
		subCategory.setSeq(customProductDTO.getSubCategoryDTO().getSeq());
		customProduct.setSubCategory(subCategory);

		customProduct.setProdcutName(customProductDTO.getProductName());
		customProduct.setInitialPrice(customProductDTO.getInitialPrice());

		customProductDao.saveOrUpdate(customProduct);
		logger.info("manageCustomProduct() >> Save Or Update Success");

		if (!CommonUtil.isEmpty(customProductDTO.getImageFile().getOriginalFilename())) {
			try {
				String oldImageName = customProduct.getImagePath();
				String imageName = ImagesUtil.uploadMultipartFile(customProductDTO.getImageFile(),
						CommonConstant.CUSTOM_PRODUCT_DIRECTORY + CommonConstant.CUSTOM_PRODUCT_IMAGE_DIRECTORY
								+ customProduct.getSeq() + "/",
						CommonConstant.CUSTOM_PRODUCT_IMAGE_PERFIX, customProduct.getSeq());
				customProduct.setImagePath(imageName);

				// delete old image path
				if (!CommonUtil.isEmpty(oldImageName)) {
					ImagesUtil.deleteFile(CommonConstant.IMAGE_SAVE_DIRECTORY, oldImageName);
				}

				customProductDao.merge(customProduct);
				logger.info("manageCustomProduct() >> Product image save is successful");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return new CustomProductDTO(customProduct);
	}

	@Override
	public CustomProductDTO getCustomProductById(long id, int status) {
		CustomProduct entity = customProductDao.getCustomProductById(id, status);
		if (entity == null) {
			logger.info("getCustomProductById() >> NULL");
			return null;
		}
		CustomProductDTO dto = new CustomProductDTO(entity);
		return dto;
	}

	@Override
	public List<CustomProductDTO> searchCustomProduct(CustomProductDTO searchCusPrdDTO) {
		List<CustomProduct> entityList = customProductDao.searchCustomProduct(searchCusPrdDTO);
		if (entityList == null || entityList.isEmpty()) {
			return new ArrayList<CustomProductDTO>();
		}

		List<CustomProductDTO> dtoList = new ArrayList<CustomProductDTO>();
		entityList.forEach(entity -> {
			dtoList.add(new CustomProductDTO(entity));
		});
		return dtoList;
	}

	@Override
	public List<CustomProductDTO> getCustomProductListBySubCat(long subCategoryId) {
		logger.info("getCustomProductListBySubCat() >> Start >> Sub Category Id : " + subCategoryId);
		List<CustomProduct> entityList = customProductDao.getCustomProductListBySubCat(subCategoryId);
		if (entityList == null || entityList.isEmpty()) {
			return new ArrayList<CustomProductDTO>();
		}

		List<CustomProductDTO> dtoList = new ArrayList<CustomProductDTO>();
		entityList.forEach(entity -> {
			CustomProductDTO dto = new CustomProductDTO(entity);
			dtoList.add(dto);
		});
		logger.info("getCustomProductListBySubCat() >> End");
		return dtoList;
	}

}
