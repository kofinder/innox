package com.finder.innox.request;

import java.io.Serializable;

public class InstockShoppingCartRequest implements Serializable {

	private static final long serialVersionUID = -5487228368998741552L;

	private long cart_id;

	private long customer_id;

	private long product_id;

	private long color_id;

	private long size_id;

	private int quantity;

	public long getShopping_cart_id() {
		return cart_id;
	}

	public void setShopping_cart_id(long shopping_cart_id) {
		this.cart_id = shopping_cart_id;
	}

	public long getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(long customer_id) {
		this.customer_id = customer_id;
	}

	public long getProduct_id() {
		return product_id;
	}

	public void setProduct_id(long product_id) {
		this.product_id = product_id;
	}

	public long getColor_id() {
		return color_id;
	}

	public void setColor_id(long color_id) {
		this.color_id = color_id;
	}

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
