package com.finder.innox.response;

import java.io.Serializable;

import com.finder.innox.core.dto.OrderDTO;

public class OrderHistoryResponse implements Serializable {

	private static final long serialVersionUID = -2553284463876536074L;

	private long order_id;

	private String invoice_number;

	private String total_cost_text;

	private String order_date;

	public OrderHistoryResponse() {
		super();
	}

	public OrderHistoryResponse(OrderDTO orderDTO) {
		if (orderDTO != null) {
			this.order_id = orderDTO.getSeq();
			this.invoice_number = orderDTO.getInvoiceNumber();
			this.total_cost_text = orderDTO.getTotalCostText();
			this.order_date = orderDTO.getOrderDate();
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

}
