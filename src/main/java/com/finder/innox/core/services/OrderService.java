package com.finder.innox.core.services;

import com.finder.innox.core.dto.OrderDTO;
import com.finder.innox.request.OrderConfirmRequest;

public interface OrderService {

	OrderDTO orderConfirm(OrderConfirmRequest confirmRequest, long customerId) throws Exception;
}
