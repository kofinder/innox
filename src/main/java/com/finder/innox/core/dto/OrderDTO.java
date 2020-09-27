package com.finder.innox.core.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.finder.innox.core.domain.Order;
import com.finder.innox.utils.CommonConstant;
import com.finder.innox.utils.CommonUtil;
import com.finder.innox.utils.OrderStatusEnum;
import com.finder.innox.utils.PaymentStatusEnum;

public class OrderDTO implements Serializable {

	private static final long serialVersionUID = -8340367362583040826L;

	private long seq;

	private UserDTO customerDTO;

	private String invoiceNumber;

	private BigDecimal totalCost;

	private String totalCostText;

	private BigDecimal deliveryFee;

	private String deliveryFeeText;

	private int orderStatus;

	private String orderStatusText;

	private int paymentStatus;

	private String paymentStatusText;

	private PaymentTypeDTO paymentTypeDTO;

	private String orderDate;

	private StateDTO stateDTO;

	private TownshipDTO townshipDTO;

	private String deliveryAddress;

	private String remark;

	public OrderDTO() {
		super();
	}

	public OrderDTO(Order order) {
		if (order != null) {
			this.seq = order.getSeq();
			this.customerDTO = new UserDTO(order.getCustomer());
			this.invoiceNumber = order.getInvoiceNumber();
			this.totalCost = order.getTotalCost();
			this.totalCostText = CommonUtil.formatBigDecimalAsCurrency(this.totalCost, CommonConstant.CURRENCY_CODE_KS);
			this.deliveryFee = order.getDeliveryFee();
			this.deliveryFeeText = CommonUtil.formatBigDecimalAsCurrency(order.getDeliveryFee(),
					CommonConstant.CURRENCY_CODE_KS);
			this.orderStatus = order.getOrderStatus() == null ? OrderStatusEnum.CANCEL.getCode()
					: order.getOrderStatus();
			this.orderStatusText = OrderStatusEnum.getDescriptionByCode(this.orderStatus);
			this.paymentStatus = order.getPaymentStatus() == null ? PaymentStatusEnum.ERROR.getCode()
					: order.getPaymentStatus();
			this.paymentStatusText = PaymentStatusEnum.getDescription(this.getPaymentStatus());
			this.paymentTypeDTO = new PaymentTypeDTO(order.getPaymentType());
			this.orderDate = CommonUtil.changeDateToString(CommonConstant.STD_DATE_FORMAT, order.getOrderDate());
			this.stateDTO = new StateDTO(order.getState());
			this.townshipDTO = new TownshipDTO(order.getTownship());
			this.deliveryAddress = order.getDeliveryAddress();
			this.remark = order.getRemark();
		}
	}

	public long getSeq() {
		return seq;
	}

	public void setSeq(long seq) {
		this.seq = seq;
	}

	public UserDTO getCustomerDTO() {
		return customerDTO;
	}

	public void setCustomerDTO(UserDTO customerDTO) {
		this.customerDTO = customerDTO;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public BigDecimal getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}

	public BigDecimal getDeliveryFee() {
		return deliveryFee;
	}

	public void setDeliveryFee(BigDecimal deliveryFee) {
		this.deliveryFee = deliveryFee;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	public int getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(int paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public PaymentTypeDTO getPaymentTypeDTO() {
		return paymentTypeDTO;
	}

	public void setPaymentTypeDTO(PaymentTypeDTO paymentTypeDTO) {
		this.paymentTypeDTO = paymentTypeDTO;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public StateDTO getStateDTO() {
		return stateDTO;
	}

	public void setStateDTO(StateDTO stateDTO) {
		this.stateDTO = stateDTO;
	}

	public TownshipDTO getTownshipDTO() {
		return townshipDTO;
	}

	public void setTownshipDTO(TownshipDTO townshipDTO) {
		this.townshipDTO = townshipDTO;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOrderStatusText() {
		return orderStatusText;
	}

	public void setOrderStatusText(String orderStatusText) {
		this.orderStatusText = orderStatusText;
	}

	public String getPaymentStatusText() {
		return paymentStatusText;
	}

	public void setPaymentStatusText(String paymentStatusText) {
		this.paymentStatusText = paymentStatusText;
	}

	public String getDeliveryFeeText() {
		return deliveryFeeText;
	}

	public void setDeliveryFeeText(String deliveryFeeText) {
		this.deliveryFeeText = deliveryFeeText;
	}

	public String getTotalCostText() {
		return totalCostText;
	}

	public void setTotalCostText(String totalCostText) {
		this.totalCostText = totalCostText;
	}

}
