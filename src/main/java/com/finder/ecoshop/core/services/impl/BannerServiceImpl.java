package com.finder.ecoshop.core.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finder.ecoshop.core.domain.Banner;
import com.finder.ecoshop.core.dto.BannerDTO;
import com.finder.ecoshop.core.services.BannerService;
import com.finder.ecoshop.repository.BannerDao;
import com.finder.ecoshop.utils.CommonConstant;
import com.finder.ecoshop.utils.CommonStatus;
import com.finder.ecoshop.utils.CommonUtil;
import com.finder.ecoshop.utils.ImagesUtil;

@Service
@Transactional
public class BannerServiceImpl implements BannerService {

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private BannerDao bannerDao;

	@Override
	public List<BannerDTO> getAllBannerList() {
		logger.info("getAllBannerList() >> Start");
		List<Banner> entityList = bannerDao.getAllBannerList();
		if (entityList == null || entityList.isEmpty()) {
			return new ArrayList<>();
		}
		List<BannerDTO> dtoList = new ArrayList<>();
		entityList.forEach(banner -> {
			BannerDTO bannerDTO = new BannerDTO(banner);
			dtoList.add(bannerDTO);
		});
		logger.info("getAllBannerList() >> End");
		return dtoList;
	}

	@Override
	public BannerDTO saveBanner(BannerDTO bannerDTO) throws Exception {
		
		Banner banner = null;
		if(bannerDTO.getSeq() > 0) {
			banner = bannerDao.get(bannerDTO.getSeq());
			banner.setName(bannerDTO.getName());
			banner.setDescription(bannerDTO.getDescription());
			banner.setSequenceNo(bannerDTO.getSequenceNo());
			banner.setStatus(CommonStatus.ACTIVE.getCode());
			banner.setUpdatedTime(new Date());
			
			bannerDao.update(banner);
		}else {
			banner = new Banner();
			banner.setName(bannerDTO.getName());
			banner.setDescription(bannerDTO.getDescription());
			banner.setSequenceNo(bannerDTO.getSequenceNo());
			banner.setStatus(CommonStatus.ACTIVE.getCode());
			banner.setCreatedTime(new Date());
			
			bannerDao.save(banner);
		}

		if (!CommonUtil.isEmpty(bannerDTO.getImageFile().getOriginalFilename())) {
			try {
				String fileName = ImagesUtil.uploadMultipartFile(bannerDTO.getImageFile(),
						CommonConstant.BANNER__DIRECTORY + CommonConstant.BANNER_IMAGE_DIRECTORY + banner.getSeq() + "/",
						CommonConstant.BANNER_IMAGE_PERFIX, banner.getSeq());
				banner.setImagePath(fileName);
			} catch (Exception e) {
				e.printStackTrace();
			}
			bannerDao.merge(banner);
		}
		
		logger.info("saveBanner() >> End >> Banner id : " + banner.getSeq());
		return new BannerDTO(bannerDao.get(banner.getSeq()));
	}

	@Override
	public BannerDTO getBannerById(Long bannerId) {
		return new BannerDTO(bannerDao.get(bannerId));
	}

}
