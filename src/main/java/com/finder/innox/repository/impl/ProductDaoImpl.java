package com.finder.innox.repository.impl;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.finder.innox.core.domain.Product;
import com.finder.innox.core.dto.ProductDTO;
import com.finder.innox.repository.ProductDao;
import com.finder.innox.utils.CommonConstant;
import com.finder.innox.utils.CommonStatus;
import com.finder.innox.utils.CommonUtil;
import com.finder.innox.utils.PopularEnum;
import com.finder.innox.utils.ProductTypeEnum;
import com.finder.innox.utils.PromotionEnum;

@SuppressWarnings({ "deprecation", "unchecked", "rawtypes" })
@Repository
public class ProductDaoImpl extends GenericDaoImpl<Product, Long> implements ProductDao {

	@Override
	public List<Product> productSearch(ProductDTO searchProductDTO) {
		Criteria c = this.getCurrentSession().createCriteria(Product.class);

		if (!CommonUtil.isEmpty(searchProductDTO.getName())) {
			c.add(Restrictions.like("name", searchProductDTO.getName(), MatchMode.ANYWHERE));
		}

		if (!CommonUtil.isEmpty(searchProductDTO.getCodeNumber())) {
			c.add(Restrictions.like("codeNumber", searchProductDTO.getCodeNumber(), MatchMode.ANYWHERE));
		}

		if (searchProductDTO.getBrandDTO() != null && searchProductDTO.getBrandDTO().getSeq() > 0) {
			c.add(Restrictions.eq("brand.seq", searchProductDTO.getBrandDTO().getSeq()));
		}

		if (searchProductDTO.getCategoryDTO() != null && searchProductDTO.getCategoryDTO().getSeq() > 0) {
			c.add(Restrictions.eq("category.seq", searchProductDTO.getCategoryDTO().getSeq()));
		}

		if (searchProductDTO.getSubCategoryDTO() != null && searchProductDTO.getSubCategoryDTO().getCategorySeq() > 0) {
			c.add(Restrictions.eq("subCategory", searchProductDTO.getSubCategoryDTO().getCategorySeq()));
		}
		
		if(searchProductDTO.getStatus() > 0) {
			c.add(Restrictions.eq("status", searchProductDTO.getStatus()));
		}

		c.addOrder(Order.asc("status"));
		return c.list();
	}

	@Override
	public List<Product> getPopularProductList() {
		String sqlStr = "from Product where isPopular = " + PopularEnum.POPULAR.getCode() + " and is_custom_product = "
				+ ProductTypeEnum.INSTOCK.getCode();
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
		c.add(Restrictions.eq("isCustomProduct", ProductTypeEnum.INSTOCK.getCode()));
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
	public List<Product> getProductListBySubCatgory(long subCategoryId, int pageNo) {
		Criteria c = this.getCurrentSession().createCriteria(Product.class);
		if (subCategoryId > 0) {
			c.add(Restrictions.eq("subCategory.seq", subCategoryId));
		}

		if (pageNo > 0) {
			int startIndex = (pageNo - 1) * CommonConstant.ROW_PER_PAGE;
			if (startIndex > -1) {
				c.setFirstResult(startIndex);
				c.setMaxResults(CommonConstant.ROW_PER_PAGE);
			}
		}

		c.add(Restrictions.eq("isCustomProduct", ProductTypeEnum.INSTOCK.getCode()));
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

		c.add(Restrictions.eq("isCustomProduct", ProductTypeEnum.INSTOCK.getCode()));
		c.add(Restrictions.eq("status", CommonStatus.ACTIVE.getCode()));
		return c.list();
	}

	@Override
	public boolean reduceItemQty(Long productId, int quantity) {
		Query query = this.getCurrentSession().createSQLQuery(
				"UPDATE product SET quantity = quantity - :qty WHERE id = :productId and is_custom_product = "
						+ ProductTypeEnum.INSTOCK.getCode());
		query.setParameter("qty", quantity);
		query.setParameter("productId", productId);
		return query.executeUpdate() > 0;
	}

	@Override
	public boolean addItemQty(Long productId, int quantity) {
		Query query = this.getCurrentSession().createSQLQuery(
				"UPDATE product SET quantity = quantity + :qty WHERE id = :productId and is_custom_product = "
						+ ProductTypeEnum.INSTOCK.getCode());
		query.setParameter("qty", quantity);
		query.setParameter("productId", productId);
		return query.executeUpdate() > 0;
	}

}
