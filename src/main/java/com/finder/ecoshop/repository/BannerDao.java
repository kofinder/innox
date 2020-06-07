package com.finder.ecoshop.repository;

import java.util.List;

import com.finder.ecoshop.core.domain.Banner;

public interface BannerDao extends GenericDao<Banner, Long>{
	
	List<Banner> getAllBannerList();
}
