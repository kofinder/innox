package com.finder.innox.response;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartListResponse implements Serializable {

	private static final long serialVersionUID = -1763549129655295272L;

	private BigDecimal total_amount;

	private String total_amount_text;

	private List<ShoppingCartResponse> shopping_carts = new ArrayList<ShoppingCartResponse>();

	public BigDecimal getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(BigDecimal total_amount) {
		this.total_amount = total_amount;
	}

	public String getTotal_amount_text() {
		return total_amount_text;
	}

	public void setTotal_amount_text(String total_amount_text) {
		this.total_amount_text = total_amount_text;
	}

	public List<ShoppingCartResponse> getShopping_carts() {
		return shopping_carts;
	}

	public void setShopping_carts(List<ShoppingCartResponse> shopping_carts) {
		this.shopping_carts = shopping_carts;
	}

}
