package com.finder.ecoshop.repository.impl;

import org.springframework.stereotype.Repository;

import com.finder.ecoshop.core.domain.ProductSize;
import com.finder.ecoshop.repository.ProductSizeDao;

@Repository
public class ProductSizeDaoImpl extends GenericDaoImpl<ProductSize, Long> implements ProductSizeDao{ 

}
