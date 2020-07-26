package com.finder.ecoshop.repository.impl;

import org.springframework.stereotype.Repository;

import com.finder.ecoshop.core.domain.CustomProduct;
import com.finder.ecoshop.repository.CustomProductDao;

@Repository
public class CustomProductDaoImpl extends GenericDaoImpl<CustomProduct, Long> implements CustomProductDao{

}
