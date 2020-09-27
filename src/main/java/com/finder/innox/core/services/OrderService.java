package com.finder.innox.core.services;

import java.util.List;

import com.finder.innox.core.dto.OrderDTO;
import com.finder.innox.request.OrderConfirmRequest;

public interface OrderService {

	OrderDTO orderConfirm(OrderConfirmRequest confirmRequest, long customerId) throws Exception;

	List<OrderDTO> getOrderHistory(List<Integer> orderStatusList, String startDate, String endDate);

	OrderDTO getOrderDTOById(long orderId);
}
