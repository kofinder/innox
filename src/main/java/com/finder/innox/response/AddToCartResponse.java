package com.finder.innox.response;

import java.io.Serializable;
import java.math.BigDecimal;

public class AddToCartResponse implements Serializable {

	private static final long serialVersionUID = 8300886224144904590L;

	private String message;

	private BigDecimal total_amount;

	private String total_amount_text;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

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

}
