package com.finder.innox.repository;

import java.util.List;

import com.finder.innox.core.domain.Brand;

public interface BrandDao extends GenericDao<Brand, Long>{
	
	List<Brand> getAllBrandList();
	
}
