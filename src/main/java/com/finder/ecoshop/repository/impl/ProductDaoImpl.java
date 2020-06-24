package com.finder.ecoshop.repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.finder.ecoshop.core.domain.Product;
import com.finder.ecoshop.core.dto.ProductDTO;
import com.finder.ecoshop.repository.ProductDao;

@Repository
public class ProductDaoImpl extends GenericDaoImpl<Product, Long> implements ProductDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> productSearch(ProductDTO searchProductDTO) {
		Criteria c = this.getCurrentSession().createCriteria(Product.class);
		return c.list();
	}

}
