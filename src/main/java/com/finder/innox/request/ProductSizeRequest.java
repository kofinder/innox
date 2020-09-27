package com.finder.innox.request;

import java.io.Serializable;

public class ProductSizeRequest implements Serializable {

	private static final long serialVersionUID = -7377164394956116584L;

	private long size_id;

	private int quantity;

	public long getSize_id() {
		return size_id;
	}

	public void setSize_id(long size_id) {
		this.size_id = size_id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
