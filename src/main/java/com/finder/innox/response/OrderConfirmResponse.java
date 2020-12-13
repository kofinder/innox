package com.finder.innox.response;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderConfirmResponse implements Serializable {

	private static final long serialVersionUID = -4513224505022303717L;

	private long order_id;

	private String invoice_number;

	private int order_status;

	private String order_status_text;

	private int payment_status;

	private String payment_status_text;

	private BigDecimal delivery_fee;

	private String delivery_fee_text;

	private BigDecimal total_item_cost;

	private String total_item_cost_text;

	private BigDecimal total_cost;

	private String total_cost_text;
	
	private boolean is_cancel_order;

	private UserResponse user_detail;

	private List<OrderItemResponse> order_items = new ArrayList<OrderItemResponse>();

	public long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(long order_id) {
		this.order_id = order_id;
	}

	public String getInvoice_number() {
		return invoice_number;
	}

	public void setInvoice_number(String invoice_number) {
		this.invoice_number = invoice_number;
	}

	public int getOrder_status() {
		return order_status;
	}

	public void setOrder_status(int order_status) {
		this.order_status = order_status;
	}

	public String getOrder_status_text() {
		return order_status_text;
	}

	public void setOrder_status_text(String order_status_text) {
		this.order_status_text = order_status_text;
	}

	public int getPayment_status() {
		return payment_status;
	}

	public void setPayment_status(int payment_status) {
		this.payment_status = payment_status;
	}

	public String getPayment_status_text() {
		return payment_status_text;
	}

	public void setPayment_status_text(String payment_status_text) {
		this.payment_status_text = payment_status_text;
	}

	public BigDecimal getDelivery_fee() {
		return delivery_fee;
	}

	public void setDelivery_fee(BigDecimal delivery_fee) {
		this.delivery_fee = delivery_fee;
	}

	public String getDelivery_fee_text() {
		return delivery_fee_text;
	}

	public void setDelivery_fee_text(String delivery_fee_text) {
		this.delivery_fee_text = delivery_fee_text;
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

	public List<OrderItemResponse> getOrder_items() {
		return order_items;
	}

	public void setOrder_items(List<OrderItemResponse> order_items) {
		this.order_items = order_items;
	}

	public UserResponse getUser_detail() {
		return user_detail;
	}

	public void setUser_detail(UserResponse user_detail) {
		this.user_detail = user_detail;
	}

	public boolean isIs_cancel_order() {
		return is_cancel_order;
	}

	public void setIs_cancel_order(boolean is_cancel_order) {
		this.is_cancel_order = is_cancel_order;
	}

}
