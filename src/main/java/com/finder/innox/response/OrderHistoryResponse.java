package com.finder.innox.response;

import java.io.Serializable;

import com.finder.innox.core.dto.OrderDTO;
import com.finder.innox.utils.OrderStatusEnum;

public class OrderHistoryResponse implements Serializable {

	private static final long serialVersionUID = -2553284463876536074L;

	private long order_id;

	private String invoice_number;

	private String total_cost_text;

	private String order_date;

	private boolean is_cancel_order;

	public OrderHistoryResponse() {
		super();
	}

	public OrderHistoryResponse(OrderDTO orderDTO) {
		if (orderDTO != null) {
			this.order_id = orderDTO.getSeq();
			this.invoice_number = orderDTO.getInvoiceNumber();
			this.total_cost_text = orderDTO.getTotalCostText();
			this.order_date = orderDTO.getOrderDate();
			this.is_cancel_order = OrderStatusEnum.PROCESSING.getCode() == orderDTO.getOrderStatus() ? true : false;
		}
	}

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

	public String getTotal_cost_text() {
		return total_cost_text;
	}

	public void setTotal_cost_text(String total_cost_text) {
		this.total_cost_text = total_cost_text;
	}

	public String getOrder_date() {
		return order_date;
	}

	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}

	public boolean isIs_cancel_order() {
		return is_cancel_order;
	}

	public void setIs_cancel_order(boolean is_cancel_order) {
		this.is_cancel_order = is_cancel_order;
	}

}
