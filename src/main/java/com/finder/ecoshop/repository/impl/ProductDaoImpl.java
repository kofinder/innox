package com.finder.ecoshop.repository.impl;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.finder.ecoshop.core.domain.Product;
import com.finder.ecoshop.core.dto.ProductDTO;
import com.finder.ecoshop.repository.ProductDao;
import com.finder.ecoshop.utils.CommonConstant;
import com.finder.ecoshop.utils.CommonStatus;
import com.finder.ecoshop.utils.CommonUtil;
import com.finder.ecoshop.utils.PopularEnum;
import com.finder.ecoshop.utils.PromotionEnum;

@SuppressWarnings({ "deprecation", "unchecked" })
@Repository
public class ProductDaoImpl extends GenericDaoImpl<Product, Long> implements ProductDao {

	@Override
	public List<Product> productSearch(ProductDTO searchProductDTO) {
		Criteria c = this.getCurrentSession().createCriteria(Product.class);
		return c.list();
	}

	@Override
	public List<Product> getPopularProductList() {
		String sqlStr = "from Product where isPopular = " + PopularEnum.POPULAR.getCode();
		Query<Product> query = this.getCurrentSession().createQuery(sqlStr);
		return query.list();
	}

	@Override
	public List<Product> getProductListByPageNo(int pageNo) {
		Criteria c = this.getCurrentSession().createCriteria(Product.class);
		if (pageNo > 0) {
			int startIndex = (pageNo - 1) * CommonConstant.ROW_PER_PAGE;
			if (startIndex > -1) {
				c.setFirstResult(startIndex);
				c.setMaxResults(CommonConstant.ROW_PER_PAGE);
			}
		}

		c.add(Restrictions.eq("status", CommonStatus.ACTIVE.getCode()));
		c.addOrder(Order.asc("name"));
		return c.list();
	}

	@Override
	public List<Product> getPromotionProductList() {
		String sqlStr = "from Product where isPromotion = " + PromotionEnum.PROMOTION.getCode();
		Query<Product> query = this.getCurrentSession().createQuery(sqlStr);
		return query.list();
	}

	@Override
	public List<Product> getProductListBySubCatgory(long subCategoryId) {
		Criteria c = this.getCurrentSession().createCriteria(Product.class);
		if (subCategoryId > 0) {
			c.add(Restrictions.eq("subCategory.seq", subCategoryId));
		}
		c.add(Restrictions.eq("status", CommonStatus.ACTIVE.getCode()));
		c.addOrder(Order.asc("createdTime"));
		c.addOrder(Order.asc("isNewArrival"));
		return c.list();
	}

	@Override
	public List<Product> searchProductList(String keyword, BigDecimal startPrice, BigDecimal endPrice, Long category,
			Long subCategory) {
		Criteria c = this.getCurrentSession().createCriteria(Product.class);

		if (!CommonUtil.isEmpty(keyword)) {
			c.add(Restrictions.like("name", keyword, MatchMode.ANYWHERE));
			c.addOrder(Order.asc("name"));
		}

		if (startPrice != null && endPrice != null) {
			c.add(Restrictions.ge("price", startPrice));
			c.add(Restrictions.le("price", endPrice));
			c.addOrder(Order.desc("price"));
		}

		if (category != null && category > 0) {
			c.add(Restrictions.eq("category.seq", category));
		}

		if (subCategory != null && subCategory > 0) {
			c.add(Restrictions.eq("subCategory.seq", subCategory));
		}

		c.add(Restrictions.eq("status", CommonStatus.ACTIVE.getCode()));
		return c.list();
	}

}
