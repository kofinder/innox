package com.finder.innox.response;

import java.io.Serializable;

public class PaymentTypeResponse implements Serializable {

	private static final long serialVersionUID = -3502021256488332741L;

	private long payment_type_id;

	private String name;

	private String code;

	private int is_offline;

	private String payment_image;

	public long getPayment_type_id() {
		return payment_type_id;
	}

	public void setPayment_type_id(long payment_type_id) {
		this.payment_type_id = payment_type_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getIs_offline() {
		return is_offline;
	}

	public void setIs_offline(int is_offline) {
		this.is_offline = is_offline;
	}

	public String getPayment_image() {
		return payment_image;
	}

	public void setPayment_image(String payment_image) {
		this.payment_image = payment_image;
	}

}
