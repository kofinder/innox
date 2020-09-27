package com.finder.innox.repository.impl;

import org.springframework.stereotype.Repository;

import com.finder.innox.core.domain.Order;
import com.finder.innox.repository.OrderDao;

@Repository
public class OrderDaoImpl extends GenericDaoImpl<Order, Long> implements OrderDao {

}
