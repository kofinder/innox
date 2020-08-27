package com.finder.innox.core.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finder.innox.core.domain.Banner;
import com.finder.innox.core.domain.User;
import com.finder.innox.core.dto.BannerDTO;
import com.finder.innox.core.services.BannerService;
import com.finder.innox.repository.BannerDao;
import com.finder.innox.utils.CommonConstant;
import com.finder.innox.utils.CommonStatus;
import com.finder.innox.utils.CommonUtil;
import com.finder.innox.utils.ImagesUtil;

@Service
@Transactional
public class BannerServiceImpl implements BannerService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

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
		if (bannerDTO.getSeq() > 0) {
			banner = bannerDao.get(bannerDTO.getSeq());
			banner.setName(bannerDTO.getName());
			banner.setDescription(bannerDTO.getDescription());
			banner.setSequenceNo(bannerDTO.getSequenceNo());
			banner.setStatus(bannerDTO.getStatus());
			banner.setUpdatedTime(new Date());
		} else {
			banner = new Banner();
			banner.setName(bannerDTO.getName());
			banner.setDescription(bannerDTO.getDescription());
			banner.setSequenceNo(bannerDTO.getSequenceNo());
			banner.setStatus(CommonStatus.ACTIVE.getCode());
			banner.setCreatedTime(new Date());
		}

		// login user
		if (bannerDTO.getUserDTO() != null && bannerDTO.getUserDTO().getSeq() > 0) {
			User user = new User();
			user.setUserSeq(bannerDTO.getUserDTO().getSeq());
			banner.setCreatedBy(user);
		}

		bannerDao.saveOrUpdate(banner);

		if (!CommonUtil.isEmpty(bannerDTO.getImageFile().getOriginalFilename())) {
			try {
				String fileName = ImagesUtil.uploadMultipartFile(bannerDTO.getImageFile(),
						CommonConstant.BANNER_DIRECTORY + CommonConstant.BANNER_IMAGE_DIRECTORY + banner.getSeq() + "/",
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
