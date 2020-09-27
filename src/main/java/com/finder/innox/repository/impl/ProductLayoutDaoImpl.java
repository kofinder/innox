package com.finder.innox.repository.impl;

import org.springframework.stereotype.Repository;

import com.finder.innox.core.domain.ProductLayout;
import com.finder.innox.repository.ProductLayoutDao;

@Repository
public class ProductLayoutDaoImpl extends GenericDaoImpl<ProductLayout, Long> implements ProductLayoutDao {

}
