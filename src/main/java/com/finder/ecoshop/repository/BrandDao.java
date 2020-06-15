package com.finder.ecoshop.repository;

import java.util.List;

import com.finder.ecoshop.core.domain.Brand;

public interface BrandDao extends GenericDao<Brand, Long>{
	
	List<Brand> getAllBrandList();
	
}
