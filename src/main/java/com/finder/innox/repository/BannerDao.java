package com.finder.innox.repository;

import java.util.List;

import com.finder.innox.core.domain.Banner;

public interface BannerDao extends GenericDao<Banner, Long>{
	
	List<Banner> getAllBannerList();
}
