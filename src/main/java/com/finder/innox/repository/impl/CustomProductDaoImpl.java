package com.finder.innox.repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.finder.innox.core.domain.CustomProduct;
import com.finder.innox.core.dto.CustomProductDTO;
import com.finder.innox.repository.CustomProductDao;
import com.finder.innox.utils.CommonUtil;

@SuppressWarnings({ "deprecation", "unchecked" })
@Repository
public class CustomProductDaoImpl extends GenericDaoImpl<CustomProduct, Long> implements CustomProductDao {

	@Override
	public List<CustomProduct> searchCustomProduct(CustomProductDTO searchCusPrdDTO) {
		Criteria c = this.getCurrentSession().createCriteria(CustomProduct.class);

		if (searchCusPrdDTO.getCategoryDTO() != null && searchCusPrdDTO.getCategoryDTO().getSeq() > 0) {
			c.add(Restrictions.eq("category.seq", searchCusPrdDTO.getCategoryDTO().getSeq()));
		}

		if (searchCusPrdDTO.getSubCategoryDTO() != null && searchCusPrdDTO.getSubCategoryDTO().getSeq() > 0) {
			c.add(Restrictions.eq("subCategory.seq", searchCusPrdDTO.getSubCategoryDTO().getSeq()));
		}

		if (searchCusPrdDTO.getStatus() > 0) {
			c.add(Restrictions.eq("status", searchCusPrdDTO.getStatus()));
		}

		if (!CommonUtil.isEmpty(searchCusPrdDTO.getProductName())) {
			c.add(Restrictions.like("prodcutName", searchCusPrdDTO.getProductName(), MatchMode.ANYWHERE));
		}

		c.addOrder(Order.asc("prodcutName"));
		return c.list();
	}

	@Override
	public List<CustomProduct> getCustomProductListBySubCat(long subCategoryId) {
		Criteria c = this.getCurrentSession().createCriteria(CustomProduct.class);
		c.add(Restrictions.eq("subCategory.seq", subCategoryId));
		c.addOrder(Order.asc("prodcutName"));
		return c.list();
	}

}
