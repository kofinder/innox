package com.finder.innox.repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.finder.innox.core.domain.OrderItem;
import com.finder.innox.repository.OrderItemDao;

@SuppressWarnings({ "deprecation", "unchecked" })
@Repository
public class OrderItemDaoImpl extends GenericDaoImpl<OrderItem, Long> implements OrderItemDao {

	@Override
	public List<OrderItem> getOrderItemListByOrderId(long orderId) {
		Criteria c = this.getCurrentSession().createCriteria(OrderItem.class);
		c.add(Restrictions.eq("order.seq", orderId));
		c.addOrder(Order.asc("createdTime"));
		return c.list();
	}

}
