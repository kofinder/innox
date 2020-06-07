package com.finder.ecoshop.core.services;

import java.util.List;

import com.finder.ecoshop.core.dto.BannerDTO;

public interface BannerService {
	
	List<BannerDTO> getAllBannerList();
	
	BannerDTO saveBanner(BannerDTO bannerDTO) throws Exception;
	
	BannerDTO getBannerById(Long bannerId);

}
