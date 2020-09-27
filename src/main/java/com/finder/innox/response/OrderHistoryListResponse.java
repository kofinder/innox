package com.finder.innox.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderHistoryListResponse implements Serializable {

	private static final long serialVersionUID = -5923807178704072746L;

	private int total_order_count;

	private List<OrderHistoryResponse> order_historys = new ArrayList<OrderHistoryResponse>();

	public List<OrderHistoryResponse> getOrder_historys() {
		return order_historys;
	}

	public void setOrder_historys(List<OrderHistoryResponse> order_historys) {
		this.order_historys = order_historys;
	}

	public int getTotal_order_count() {
		return total_order_count;
	}

	public void setTotal_order_count(int total_order_count) {
		this.total_order_count = total_order_count;
	}

}
