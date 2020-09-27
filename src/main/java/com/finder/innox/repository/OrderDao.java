package com.finder.innox.repository;

import java.util.List;

import com.finder.innox.core.domain.Order;

public interface OrderDao extends GenericDao<Order, Long> {

	List<Order> getOrderHistory(List<Integer> orderStatusList, String startDate, String endDate);

}
