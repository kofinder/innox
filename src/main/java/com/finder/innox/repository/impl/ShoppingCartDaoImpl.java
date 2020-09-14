package com.finder.innox.repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.finder.innox.core.domain.ShoppingCart;
import com.finder.innox.repository.ShoppingCartDao;

@SuppressWarnings({ "rawtypes", "deprecation", "unchecked" })
@Repository
public class ShoppingCartDaoImpl extends GenericDaoImpl<ShoppingCart, Long> implements ShoppingCartDao {

	@Override
	public ShoppingCart getDataById(long customerId, long shoppingCartId, long productId, long colorId, long sizeId) {
		Criteria c = this.getCurrentSession().createCriteria(ShoppingCart.class);

		if (customerId > 0) {
			c.add(Restrictions.eq("customer.userSeq", customerId));
		}

		if (shoppingCartId > 0) {
			c.add(Restrictions.eq("seq", shoppingCartId));
		}

		if (productId > 0) {
			c.add(Restrictions.eq("product.seq", productId));
		}

		if (colorId > 0) {
			c.add(Restrictions.eq("color.seq", colorId));
		}

		if (sizeId > 0) {
			c.add(Restrictions.eq("size.seq", sizeId));
		}
		return (ShoppingCart) c.uniqueResult();
	}

	@Override
	public void updateShoppingCart(long id, int quantity) {
		String sqlStr = "UPDATE `shopping_cart` SET quantity = " + quantity + " WHERE id = " + id;
		Query query = this.getCurrentSession().createSQLQuery(sqlStr);
		query.executeUpdate();
	}

	@Override
	public List<ShoppingCart> getShoppingCartDataByIds(long customerId, long cartId, long productId) {
		Criteria c = this.getCurrentSession().createCriteria(ShoppingCart.class);
		c.add(Restrictions.eq("customer.userSeq", customerId));

		if (cartId > 0) {
			c.add(Restrictions.eq("seq", cartId));
		}

		if (productId > 0) {
			c.add(Restrictions.eq("product.seq", productId));
		}

		c.addOrder(Order.asc("createdTime"));
		return c.list();
	}

}
