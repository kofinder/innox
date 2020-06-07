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
import com.finder.ecoshop.core.dto.BrandDTO;
import com.finder.ecoshop.core.services.BrandService;
import com.finder.ecoshop.repository.BrandDao;
import com.finder.ecoshop.utils.CommonConstant;
import com.finder.ecoshop.utils.CommonStatus;
import com.finder.ecoshop.utils.CommonUtil;
import com.finder.ecoshop.utils.ImagesUtil;

@Service
@Transactional
public class BrandServiceImpl implements BrandService{
	
	private final Logger logger = LogManager.getLogger(this.getClass());
	
	@Autowired
	private BrandDao brandDao;

	@Override
	public List<BrandDTO> getAllBannerList() {
		List<Brand> brandList = brandDao.getAll();
		if(brandList == null || brandList.isEmpty()) {
			return new ArrayList<BrandDTO>();
		}
		List<BrandDTO> dtoList = new ArrayList<>();
		brandList.forEach(brand -> {
			dtoList.add(new BrandDTO(brand));
		});
		return dtoList;
	}

	@Override
	public BrandDTO saveBrand(BrandDTO brandDTO) throws Exception {
		Brand brand = null;
		
		if(brandDTO.getSeq() <= 0) {
			// save
			brand = new Brand();
			brand.setName(brandDTO.getName());
			brand.setSequence(brandDTO.getSequence());
			brand.setStatus(CommonStatus.ACTIVE.getCode());
			brand.setCreatedTime(new Date());
			
			brandDao.save(brand);
		}else {
			// update
			brand = brandDao.get(brandDTO.getSeq());
			brand.setName(brandDTO.getName());
			brand.setSequence(brandDTO.getSequence());
			brand.setStatus(brandDTO.getStatus());
			brand.setCreatedTime(new Date());
			
			brandDao.update(brand);
		}
		
		if (!CommonUtil.isEmpty(brandDTO.getImageFile().getOriginalFilename())) {
			try {
				String fileName = ImagesUtil.uploadMultipartFile(brandDTO.getImageFile(),
						CommonConstant.BRAND__DIRECTORY + CommonConstant.BRAND_IMAGE_DIRECTORY + brand.getSeq() + "/",
						CommonConstant.BRAND_IMAGE_PERFIX, brand.getSeq());
				brand.setImagePath(fileName);
			} catch (Exception e) {
				e.printStackTrace();
			}
			brandDao.merge(brand);
		}
		
		logger.info("saveBrand() >> End >> Brand id : " + brand.getSeq());
		return new BrandDTO(brandDao.get(brand.getSeq()));
	}

	@Override
	public BrandDTO getBrandById(Long brandId) {
		Brand brand = brandDao.get(brandId);
		if(brand == null) {
			return new BrandDTO();
		}
		return new BrandDTO(brand);
	}

}
