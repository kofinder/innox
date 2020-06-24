package com.finder.ecoshop.core.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finder.ecoshop.core.domain.Brand;
import com.finder.ecoshop.core.domain.Category;
import com.finder.ecoshop.core.domain.Product;
import com.finder.ecoshop.core.domain.SubCategory;
import com.finder.ecoshop.core.dto.ProductDTO;
import com.finder.ecoshop.core.services.ProductService;
import com.finder.ecoshop.repository.ProductDao;
import com.finder.ecoshop.utils.CommonConstant;
import com.finder.ecoshop.utils.CommonStatus;
import com.finder.ecoshop.utils.CommonUtil;
import com.finder.ecoshop.utils.ImagesUtil;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private ProductDao productDao;

	@Override
	public ProductDTO manageProduct(ProductDTO productDTO) {

		Product product = new Product();
		
		if (productDTO.getSeq() > 0) { // update
			product = productDao.get(productDTO.getSeq());
			product.setStatus(productDTO.getStatus());
			product.setUpdatedTime(new Date());

		} else { // save
			product.setStatus(CommonStatus.ACTIVE.getCode());
			product.setCreatedTime(new Date());
		}

		product.setName(productDTO.getName());
		product.setCodeNumber(productDTO.getCodeNumber());

		Brand brand = new Brand();
		brand.setSeq(productDTO.getBrandDTO().getSeq());
		product.setBrand(brand);

		Category category = new Category();
		category.setSeq(productDTO.getCategoryDTO().getSeq());
		product.setCategory(category);

		SubCategory subCategory = new SubCategory();
		subCategory.setSeq(productDTO.getSubCategoryDTO().getSeq());
		product.setSubCategory(subCategory);

		product.setPrice(productDTO.getPrice());
		product.setIsPopular(productDTO.isPopular());
		product.setIsPromotion(productDTO.isPromotion());
		product.setOriginalPrice(productDTO.getOriginalPrice());
		product.setDiscountPercent(productDTO.getDiscountPercent());
		product.setOverview(productDTO.getOverview());
		product.setDetail(productDTO.getDetail());

		productDao.saveOrUpdate(product);
		logger.info("manageProduct() >> Product Save or Update is successful");

		if (!CommonUtil.isEmpty(productDTO.getImageFile1().getOriginalFilename())) {
			try {
				String oldImageName = product.getImagePath1();
				String imageName = ImagesUtil.uploadMultipartFile(
						productDTO.getImageFile1(), CommonConstant.PRODUCT_DIRECTORY
								+ CommonConstant.PRODUCT_IMAGE_DIRECTORY + product.getSeq() + "/",
						CommonConstant.PRODUCT_IMAGE_PERFIX, product.getSeq());
				product.setImagePath1(imageName);
				
				// delete old image path
				if(!CommonUtil.isEmpty(oldImageName)) {
					ImagesUtil.deleteFile(CommonConstant.IMAGE_SAVE_DIRECTORY, oldImageName);
				}
				
				productDao.merge(product);
				logger.info("manageProduct() >> Product image 1 save is successful");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (!CommonUtil.isEmpty(productDTO.getImageFile2().getOriginalFilename())) {
			try {
				String oldImageName = product.getImagePath2();
				String imageName = ImagesUtil.uploadMultipartFile(
						productDTO.getImageFile2(), CommonConstant.PRODUCT_DIRECTORY
								+ CommonConstant.PRODUCT_IMAGE_DIRECTORY + product.getSeq() + "/",
						CommonConstant.PRODUCT_IMAGE_PERFIX, product.getSeq());
				product.setImagePath2(imageName);

				if(!CommonUtil.isEmpty(oldImageName)) {
					ImagesUtil.deleteFile(CommonConstant.IMAGE_SAVE_DIRECTORY, oldImageName);
				}
				
				productDao.merge(product);
				logger.info("manageProduct() >> Product image 2 save is successful");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (!CommonUtil.isEmpty(productDTO.getImageFile3().getOriginalFilename())) {
			try {
				String oldImageName = product.getImagePath3();
				String imageName = ImagesUtil.uploadMultipartFile(
						productDTO.getImageFile3(), CommonConstant.PRODUCT_DIRECTORY
								+ CommonConstant.PRODUCT_IMAGE_DIRECTORY + product.getSeq() + "/",
						CommonConstant.PRODUCT_IMAGE_PERFIX, product.getSeq());
				product.setImagePath3(imageName);

				if(!CommonUtil.isEmpty(oldImageName)) {
					ImagesUtil.deleteFile(CommonConstant.IMAGE_SAVE_DIRECTORY, oldImageName);
				}
				
				productDao.merge(product);
				logger.info("manageProduct() >> Product image 3 save is successful");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (!CommonUtil.isEmpty(productDTO.getImageFile4().getOriginalFilename())) {
			try {
				String oldImageName = product.getImagePath4();
				String imageName = ImagesUtil.uploadMultipartFile(
						productDTO.getImageFile4(), CommonConstant.PRODUCT_DIRECTORY
								+ CommonConstant.PRODUCT_IMAGE_DIRECTORY + product.getSeq() + "/",
						CommonConstant.PRODUCT_IMAGE_PERFIX, product.getSeq());
				product.setImagePath4(imageName);

				if(!CommonUtil.isEmpty(oldImageName)) {
					ImagesUtil.deleteFile(CommonConstant.IMAGE_SAVE_DIRECTORY, oldImageName);
				}
				
				productDao.merge(product);
				logger.info("manageProduct() >> Product image 4 save is successful");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		logger.info("manageProduct() >> Product manage finished");
		return new ProductDTO(product);
	}

	@Override
	public List<ProductDTO> productSearch(ProductDTO searchProductDTO) {
		logger.info("productSearch() >> Start");
		List<Product> productList = productDao.productSearch(searchProductDTO);
		if(productList == null || productList.isEmpty()) {
			return new ArrayList<ProductDTO>();
		}
		
		List<ProductDTO> dtoList = new ArrayList<ProductDTO>();
		productList.forEach(product -> {
			dtoList.add(new ProductDTO(product));
		});
		logger.info("productSearch() >> End");
		return dtoList;
	}

	@Override
	public ProductDTO getProductDataById(Long prdId) {
		logger.info("getProductDataById() >> Start >> Product Id : " + prdId);
		Product product = productDao.get(prdId);
		if(product == null) {
			return null;
		}
		
		ProductDTO productDTO = new ProductDTO(product);
		return productDTO;
	}

}
