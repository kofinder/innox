package com.finder.ecoshop.core.services.impl;

import java.util.Date;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finder.ecoshop.core.domain.Category;
import com.finder.ecoshop.core.domain.CustomProduct;
import com.finder.ecoshop.core.domain.SubCategory;
import com.finder.ecoshop.core.domain.User;
import com.finder.ecoshop.core.dto.CustomProductDTO;
import com.finder.ecoshop.core.services.CustomProductService;
import com.finder.ecoshop.repository.CustomProductDao;
import com.finder.ecoshop.repository.UserDao;
import com.finder.ecoshop.utils.CommonConstant;
import com.finder.ecoshop.utils.CommonStatus;
import com.finder.ecoshop.utils.CommonUtil;
import com.finder.ecoshop.utils.ImagesUtil;

@Service
@Transactional
public class CustomProductServiceImpl implements CustomProductService {

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private UserDao userDao;

	@Autowired
	private CustomProductDao customProductDao;

	@Override
	public CustomProductDTO manageCustomProduct(CustomProductDTO customProductDTO, String loginUserName) {
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
	public CustomProductDTO getCustomProductById(long id) {
		CustomProduct entity = customProductDao.get(id);
		if (entity == null) {
			logger.info("getCustomProductById() >> NULL");
			return null;
		}
		CustomProductDTO dto = new CustomProductDTO(entity);
		return dto;
	}

}
