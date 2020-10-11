package com.finder.innox.core.services.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finder.innox.core.domain.Order;
import com.finder.innox.core.domain.OrderItem;
import com.finder.innox.core.domain.PaymentType;
import com.finder.innox.core.domain.ShoppingCart;
import com.finder.innox.core.domain.State;
import com.finder.innox.core.domain.Township;
import com.finder.innox.core.domain.User;
import com.finder.innox.core.dto.OrderDTO;
import com.finder.innox.core.services.OrderService;
import com.finder.innox.repository.OrderDao;
import com.finder.innox.repository.OrderItemDao;
import com.finder.innox.repository.PaymentTypeDao;
import com.finder.innox.repository.ProductDao;
import com.finder.innox.repository.ShoppingCartDao;
import com.finder.innox.repository.TownshipDao;
import com.finder.innox.request.OrderConfirmRequest;
import com.finder.innox.utils.CommonConstant;
import com.finder.innox.utils.CommonUtil;
import com.finder.innox.utils.OrderStatusEnum;
import com.finder.innox.utils.PaymentStatusEnum;
import com.finder.innox.utils.ProductTypeEnum;

@Service
@Transactional
public class OrderServiceImple implements OrderService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private OrderItemDao orderItemDao;

	@Autowired
	private ShoppingCartDao shoppingCartDao;

	@Autowired
	private PaymentTypeDao paymentTypeDao;

	@Autowired
	private TownshipDao TownshipDao;

	@Autowired
	private ProductDao productDao;

	@Override
	public OrderDTO orderConfirm(OrderConfirmRequest confirmRequest, long customerId) throws Exception {

		if (CommonUtil.isEmpty(confirmRequest.getPaymen_type_code())) {
			logger.info("orderConfirm() >> Invalid payment type code : " + confirmRequest.getPaymen_type_code());
			return null;
		}

		if (confirmRequest.getState_id() <= 0) {
			logger.info("orderConfirm() >> Invalid state id : " + confirmRequest.getState_id());
			return null;
		}

		if (confirmRequest.getTownship_id() <= 0) {
			logger.info("orderConfirm() >> Invalid township id : " + confirmRequest.getTownship_id());
			return null;
		}

		List<ShoppingCart> shoppingCarts = shoppingCartDao
				.getShoppingCartsForOrderPreload(confirmRequest.getCart_ids());
		if (shoppingCarts == null || shoppingCarts.isEmpty()) {
			logger.info("orderConfirm() >> Invalid shopping cart list : " + confirmRequest.getCart_ids());
			return null;
		}

		Order order = new Order();

		User user = new User();
		user.setUserSeq(customerId);
		order.setCustomer(user);

		// generate order no
		StringBuilder seqNo = new StringBuilder();
		seqNo.append(CommonConstant.ORDER_SEQ_NO_PREFIX);
		seqNo.append(CommonUtil.generateTransactionRefNo());
		order.setInvoiceNumber(seqNo.toString());

		BigDecimal totalCost = shoppingCarts.stream()
				.map(item -> item.getProduct().getPrice().multiply(new BigDecimal(item.getQuantity())))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		order.setTotalCost(totalCost);

		order.setOrderStatus(OrderStatusEnum.PROCESSING.getCode());
		order.setPaymentStatus(PaymentStatusEnum.PENDING.getCode());

		PaymentType paymentType = paymentTypeDao.getPaymentTypeByCode(confirmRequest.getPaymen_type_code());
		order.setPaymentType(paymentType);

		order.setOrderDate(new Date());

		State state = new State();
		state.setSeq(confirmRequest.getState_id());
		order.setState(state);

		Township township = TownshipDao.get(confirmRequest.getTownship_id());
		order.setTownship(township);

		// delivery fee
		if (township != null && township.getZone() != null
				&& township.getZone().getDeliveryFee().compareTo(BigDecimal.ZERO) > 0) {
			order.setDeliveryFee(township.getZone().getDeliveryFee());

			order.setTotalCost(order.getTotalCost().add(order.getDeliveryFee()));
		} else {
			order.setDeliveryFee(BigDecimal.ZERO);
		}

		order.setDeliveryAddress(confirmRequest.getDetail_address());
		order.setRemark(confirmRequest.getRemark());
		order.setCreatedTime(new Date());
		order.setUpdatedTime(new Date());

		Long orderId = orderDao.saveWithId(order);
		logger.info("orderConfirm() >> Order save is success >> Order Id : " + orderId);

		if (orderId != null && orderId.compareTo(0L) >= 0) {
			logOrder("Order record saved successfully", order);

			for (ShoppingCart shoppingCart : shoppingCarts) {
				OrderItem orderItem = new OrderItem();

				orderItem.setOrder(order);
				orderItem.setProduct(shoppingCart.getProduct());
				orderItem.setUnitPrice(shoppingCart.getProduct().getPrice());
				orderItem.setQuantity(shoppingCart.getQuantity());
				orderItem.setSubTotal(
						shoppingCart.getProduct().getPrice().multiply(new BigDecimal(shoppingCart.getQuantity())));
				orderItem.setCreatedTime(new Date());
				orderItem.setUpdatedTime(new Date());

				Long orderItemId = orderItemDao.saveWithId(orderItem);

				if (orderItemId != null && orderItemId.compareTo(0L) > 0) {
					logOrderItem("Order item record saved successfully", orderItem);
					if (productDao.reduceItemQty(orderItem.getProduct().getSeq(), orderItem.getQuantity())) {
						logger.info(
								"confirmOrder() >> Reducing product quantity after order item saved successfully is successful >> Product Id : "
										+ shoppingCart.getProduct().getSeq() + ", Qty : " + shoppingCart.getQuantity());
					} else {
						logger.info(
								"confirmOrder() >> Reducing product quantity after order item saved successfully is failed >> productId : "
										+ shoppingCart.getProduct().getSeq() + ", Qty : " + shoppingCart.getQuantity());
					}
				} else {
					logOrderItem("Order item record saving failed", orderItem);
					return null;
				}
			}

			// delete existing shopping cart list
			if (shoppingCartDao.deleteDataByIdList(confirmRequest.getCart_ids())) {
				logger.info(
						">>> Deleted successfuly the existing shopping cart records after saving order item is successful >>> cardIdList = "
								+ confirmRequest.getCart_ids());
			} else {
				logger.info(
						">>> Deleting failed the existing shopping cart records after saving order item is successful >>> cardIdList = "
								+ confirmRequest.getCart_ids());
				return null;
			}

			// order info
			OrderDTO orderDTO = new OrderDTO(orderDao.get(orderId));

			return orderDTO;
		} else {
			logOrder("Order record saving failed", order);
			return null;
		}
	}

	private void logOrder(String messagePrefix, Order order) {

		StringBuilder orderInfo = new StringBuilder();
		orderInfo.append("id = ");
		orderInfo.append(order.getSeq());
		orderInfo.append(", customerId = ");
		orderInfo.append(order.getCustomer().getUserSeq());
		orderInfo.append(", invoiceNumber = ");
		orderInfo.append(order.getInvoiceNumber());
		orderInfo.append(", totalCost = ");
		orderInfo.append(order.getTotalCost());
		orderInfo.append(", status = ");
		orderInfo.append(order.getOrderStatus());
		orderInfo.append(", deliveryAddress = ");
		orderInfo.append((order.getDeliveryAddress() != null) ? order.getDeliveryAddress() : "null");
		orderInfo.append(", paymentStatus = ");
		orderInfo.append(order.getPaymentStatus());
		orderInfo.append(", paymentTypeId = ");
		orderInfo.append((order.getPaymentType() != null) ? order.getPaymentType().getSeq() : "null");
		orderInfo.append(", remark = ");
		orderInfo.append(order.getRemark());

		logger.info(String.format(">>> %s >>> %s", messagePrefix, orderInfo.toString()));
	}

	private void logOrderItem(String messagePrefix, OrderItem orderItem) {
		StringBuilder orderItemInfo = new StringBuilder();
		orderItemInfo.append("id = ");
		orderItemInfo.append(orderItem.getSeq());
		orderItemInfo.append(", orderId = ");
		orderItemInfo.append((orderItem.getOrder() != null) ? orderItem.getOrder().getSeq() : "null");
		orderItemInfo.append(", productId = ");
		orderItemInfo.append((orderItem.getProduct() != null) ? orderItem.getProduct().getSeq() : "null");
		orderItemInfo.append(", qty = ");
		orderItemInfo.append(orderItem.getQuantity());
		orderItemInfo.append(", price = ");
		orderItemInfo.append(orderItem.getUnitPrice());

		logger.info(String.format(">>> %s >>> %s", messagePrefix, orderItemInfo.toString()));
	}

	@Override
	public List<OrderDTO> getOrderHistory(List<Integer> orderStatusList, String startDate, String endDate, long customerId) {
		logger.info("getOrderHistory() >> " + "Order Status : " + orderStatusList.toString() + " >> Start Date : "
				+ startDate + " >> End Date : " + endDate);
		List<Order> orderList = orderDao.getOrderHistory(orderStatusList, startDate, endDate, customerId);
		if (orderList == null || orderList.isEmpty()) {
			return new ArrayList<OrderDTO>();
		}

		List<OrderDTO> dtoList = new ArrayList<OrderDTO>();
		orderList.forEach(order -> {
			dtoList.add(new OrderDTO(order));
		});
		return dtoList;
	}

	@Override
	public OrderDTO getOrderDTOById(long orderId) {
		logger.info("getOrderDTOById() >> Order Id : " + orderId);
		Order order = orderDao.get(orderId);
		if (order != null) {
			return new OrderDTO(order);
		}
		return null;
	}

	@Override
	public List<OrderDTO> searchOrderHistoryList(OrderDTO orderDTO) {

		if (!CommonUtil.isEmpty(orderDTO.getDateRange())) {
			String[] dateReangeArr = orderDTO.getDateRange().split("-");

			if (dateReangeArr.length == 2) {
				orderDTO.setFromDate(dateReangeArr[0]);
				orderDTO.setToDate(dateReangeArr[1]);
			}
		}

		List<Order> entityList = orderDao.searchOrderHistoryList(orderDTO);
		if (entityList != null) {
			List<OrderDTO> dtoList = new ArrayList<OrderDTO>();

			entityList.forEach(order -> {
				dtoList.add(new OrderDTO(order));
			});
			return dtoList;
		}
		return null;
	}

	@Override
	public OrderDTO orderManage(OrderDTO orderDTO) throws Exception {
		logger.info("orderManage() >> Order Id : " + orderDTO.getSeq() + " >>> Payment Status : "
				+ orderDTO.getOrderStatus() + " >>> " + orderDTO.getPaymentStatus());
		orderDao.updateOrderStatus(orderDTO.getSeq(), orderDTO.getOrderStatus(), orderDTO.getPaymentStatus());

		if (OrderStatusEnum.CANCEL.getCode() == orderDTO.getOrderStatus()) {
			List<OrderItem> orderItemList = orderItemDao.getOrderItemListByOrderId(orderDTO.getSeq());

			orderItemList.forEach(orderItem -> {
				if (orderItem.getProduct().getIsCustomProduct() == ProductTypeEnum.INSTOCK.getCode()) {
					productDao.addItemQty(orderItem.getSeq(), orderItem.getQuantity());
				}
			});
		}
		return new OrderDTO(orderDao.get(orderDTO.getSeq()));
	}

}
