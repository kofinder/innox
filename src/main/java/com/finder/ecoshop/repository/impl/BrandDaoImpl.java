package com.finder.ecoshop.repository.impl;

import org.springframework.stereotype.Repository;

import com.finder.ecoshop.core.domain.Brand;
import com.finder.ecoshop.repository.BrandDao;

@Repository
public class BrandDaoImpl extends GenericDaoImpl<Brand, Long> implements BrandDao{

}
