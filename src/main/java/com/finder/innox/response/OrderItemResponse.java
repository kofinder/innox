package com.finder.innox.response;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderItemResponse implements Serializable {

	private static final long serialVersionUID = 6239871571303124449L;

	private long cart_id;

	private long product_id;

	private String product_name;

	private String color_name;

	private String size_name;

	private String image_path;

	private BigDecimal unit_price;

	private String unit_price_text;

	private int quantity;

	private BigDecimal sub_total;

	private String sub_total_text;

	public long getCart_id() {
		return cart_id;
	}

	public void setCart_id(long cart_id) {
		this.cart_id = cart_id;
	}

	public long getProduct_id() {
		return product_id;
	}

	public void setProduct_id(long product_id) {
		this.product_id = product_id;
	}

	public BigDecimal getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(BigDecimal unit_price) {
		this.unit_price = unit_price;
	}

	public String getUnit_price_text() {
		return unit_price_text;
	}

	public void setUnit_price_text(String unit_price_text) {
		this.unit_price_text = unit_price_text;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getSub_total() {
		return sub_total;
	}

	public void setSub_total(BigDecimal sub_total) {
		this.sub_total = sub_total;
	}

	public String getSub_total_text() {
		return sub_total_text;
	}

	public void setSub_total_text(String sub_total_text) {
		this.sub_total_text = sub_total_text;
	}

	public String getImage_path() {
		return image_path;
	}

	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getColor_name() {
		return color_name;
	}

	public void setColor_name(String color_name) {
		this.color_name = color_name;
	}

	public String getSize_name() {
		return size_name;
	}

	public void setSize_name(String size_name) {
		this.size_name = size_name;
	}

}
