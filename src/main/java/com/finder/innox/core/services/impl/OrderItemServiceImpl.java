package com.finder.innox.core.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finder.innox.core.domain.OrderItem;
import com.finder.innox.core.dto.OrderItemDTO;
import com.finder.innox.core.services.OrderItemService;
import com.finder.innox.repository.OrderItemDao;

@Service
@Transactional
public class OrderItemServiceImpl implements OrderItemService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private OrderItemDao orderItemDao;

	@Override
	public List<OrderItemDTO> getOrderItemListByOrderId(long orderId) {
		logger.info("getOrderItemListByOrderId() >> Order Id : " + orderId);
		List<OrderItem> entityList = orderItemDao.getOrderItemListByOrderId(orderId);
		if (entityList == null || entityList.isEmpty()) {
			return new ArrayList<OrderItemDTO>();
		}

		List<OrderItemDTO> dtoList = new ArrayList<OrderItemDTO>();
		entityList.forEach(orderItem -> {
			dtoList.add(new OrderItemDTO(orderItem));
		});
		return dtoList;
	}

}
