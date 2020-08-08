package com.finder.innox.core.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finder.innox.core.domain.Brand;
import com.finder.innox.core.dto.BrandDTO;
import com.finder.innox.core.services.BrandService;
import com.finder.innox.repository.BrandDao;
import com.finder.innox.utils.CommonConstant;
import com.finder.innox.utils.CommonStatus;
import com.finder.innox.utils.CommonUtil;
import com.finder.innox.utils.ImagesUtil;

@Service
@Transactional
public class BrandServiceImpl implements BrandService{
	
	private final Logger logger = LogManager.getLogger(this.getClass());
	
	@Autowired
	private BrandDao brandDao;

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
						CommonConstant.BRAND_DIRECTORY + CommonConstant.BRAND_IMAGE_DIRECTORY + brand.getSeq() + "/",
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

	@Override
	public List<BrandDTO> getAllBrandList() {
		List<Brand> brandList = brandDao.getAllBrandList();
		if(brandList == null || brandList.isEmpty()) {
			return new ArrayList<BrandDTO>();
		}
		List<BrandDTO> dtoList = new ArrayList<>();
		brandList.forEach(brand -> {
			dtoList.add(new BrandDTO(brand));
		});
		return dtoList;
	}

}
