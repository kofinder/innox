package com.finder.innox.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderPreloadRequest implements Serializable {

	private static final long serialVersionUID = -4225689473063879026L;

	private List<Long> cart_ids = new ArrayList<Long>();

	public List<Long> getCart_ids() {
		return cart_ids;
	}

	public void setCart_ids(List<Long> cart_ids) {
		this.cart_ids = cart_ids;
	}

}
