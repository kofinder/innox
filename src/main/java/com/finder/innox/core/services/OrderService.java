package com.finder.innox.core.services;

import java.util.List;

import com.finder.innox.core.dto.OrderDTO;
import com.finder.innox.request.OrderConfirmRequest;

public interface OrderService {

	OrderDTO orderConfirm(OrderConfirmRequest confirmRequest, long customerId) throws Exception;

	List<OrderDTO> getOrderHistory(List<Integer> orderStatusList, String startDate, String endDate, long customerId);

	OrderDTO getOrderDTOById(long orderId);

	List<OrderDTO> searchOrderHistoryList(OrderDTO orderDTO);

	OrderDTO orderManage(OrderDTO orderDTO) throws Exception;

	OrderDTO cancelOrder(long orderId) throws Exception;
}
