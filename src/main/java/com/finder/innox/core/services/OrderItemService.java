package com.finder.innox.core.services;

import java.util.List;

import com.finder.innox.core.dto.OrderItemDTO;

public interface OrderItemService {

	List<OrderItemDTO> getOrderItemListByOrderId(long orderId);

}
