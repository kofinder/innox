package com.finder.innox.response;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderPreloadResponse implements Serializable {

	private static final long serialVersionUID = 2168178999692163037L;

	private BigDecimal total_cost = BigDecimal.ZERO;

	private String total_cost_text;

	private BigDecimal total_item_cost = BigDecimal.ZERO;

	private String total_item_cost_text;

	private BigDecimal delivery_cost = BigDecimal.ZERO;

	private String delivery_cost_text;

	private UserResponse user_detail;

	private List<PaymentTypeResponse> payment_types = new ArrayList<PaymentTypeResponse>();

	private List<OrderItemResponse> order_items = new ArrayList<OrderItemResponse>();

	public BigDecimal getTotal_cost() {
		return total_cost;
	}

	public void setTotal_cost(BigDecimal total_cost) {
		this.total_cost = total_cost;
	}

	public String getTotal_cost_text() {
		return total_cost_text;
	}

	public void setTotal_cost_text(String total_cost_text) {
		this.total_cost_text = total_cost_text;
	}

	public BigDecimal getTotal_item_cost() {
		return total_item_cost;
	}

	public void setTotal_item_cost(BigDecimal total_item_cost) {
		this.total_item_cost = total_item_cost;
	}

	public String getTotal_item_cost_text() {
		return total_item_cost_text;
	}

	public void setTotal_item_cost_text(String total_item_cost_text) {
		this.total_item_cost_text = total_item_cost_text;
	}

	public BigDecimal getDelivery_cost() {
		return delivery_cost;
	}

	public void setDelivery_cost(BigDecimal delivery_cost) {
		this.delivery_cost = delivery_cost;
	}

	public String getDelivery_cost_text() {
		return delivery_cost_text;
	}

	public void setDelivery_cost_text(String delivery_cost_text) {
		this.delivery_cost_text = delivery_cost_text;
	}

	public UserResponse getUser_detail() {
		return user_detail;
	}

	public void setUser_detail(UserResponse user_detail) {
		this.user_detail = user_detail;
	}

	public List<PaymentTypeResponse> getPayment_types() {
		return payment_types;
	}

	public void setPayment_types(List<PaymentTypeResponse> payment_types) {
		this.payment_types = payment_types;
	}

	public List<OrderItemResponse> getOrder_items() {
		return order_items;
	}

	public void setOrder_items(List<OrderItemResponse> order_items) {
		this.order_items = order_items;
	}

}
