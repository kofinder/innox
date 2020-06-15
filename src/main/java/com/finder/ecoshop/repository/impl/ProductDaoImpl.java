package com.finder.ecoshop.repository.impl;

import org.springframework.stereotype.Repository;

import com.finder.ecoshop.core.domain.Product;
import com.finder.ecoshop.repository.ProductDao;

@Repository
public class ProductDaoImpl extends GenericDaoImpl<Product, Long> implements ProductDao{

}
