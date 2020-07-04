package com.finder.ecoshop.repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.finder.ecoshop.core.domain.Product;
import com.finder.ecoshop.core.dto.ProductDTO;
import com.finder.ecoshop.repository.ProductDao;
import com.finder.ecoshop.utils.CommonConstant;
import com.finder.ecoshop.utils.CommonStatus;
import com.finder.ecoshop.utils.PopularEnum;

@SuppressWarnings("deprecation")
@Repository
public class ProductDaoImpl extends GenericDaoImpl<Product, Long> implements ProductDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> productSearch(ProductDTO searchProductDTO) {
		Criteria c = this.getCurrentSession().createCriteria(Product.class);
		return c.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getPopularProductList() {
		String sqlStr = "from Product where isPopular = " + PopularEnum.POPULAR.getCode();
		Query<Product> query = this.getCurrentSession().createQuery(sqlStr);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getProductListByPageNo(int pageNo) {
		Criteria c = this.getCurrentSession().createCriteria(Product.class);
		if(pageNo > 0) {
			int startIndex = (pageNo - 1) * CommonConstant.ROW_PER_PAGE;
			if(startIndex > -1) {
				c.setFirstResult(startIndex);
				c.setMaxResults(CommonConstant.ROW_PER_PAGE);
			}
		}
		
		c.add(Restrictions.eq("status", CommonStatus.ACTIVE.getCode()));
		c.addOrder(Order.asc("name"));
		return c.list();
	}

}
