package com.finder.innox.repository;

import java.util.List;

import com.finder.innox.core.domain.Order;
import com.finder.innox.core.dto.OrderDTO;

public interface OrderDao extends GenericDao<Order, Long> {

	List<Order> getOrderHistory(List<Integer> orderStatusList, String startDate, String endDate, long customerId);

	List<Order> searchOrderHistoryList(OrderDTO orderDTO);
	
	void updateOrderStatus(long orderId, int orderStatus, int paymentStatus);

}
