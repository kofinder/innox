package com.finder.innox.repository;

import java.util.List;

import com.finder.innox.core.domain.OrderItem;

public interface OrderItemDao extends GenericDao<OrderItem, Long> {

	List<OrderItem> getOrderItemListByOrderId(long orderId);
}
