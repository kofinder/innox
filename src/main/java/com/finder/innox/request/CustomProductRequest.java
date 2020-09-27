package com.finder.innox.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CustomProductRequest implements Serializable {

	private static final long serialVersionUID = 2907067212906767767L;

	private long custom_product_id;

	private long custom_item_id;

	private List<ProductLayoutRequest> product_layout = new ArrayList<ProductLayoutRequest>();

	private List<ProductSizeRequest> product_sizes = new ArrayList<ProductSizeRequest>();

	public long getCustom_product_id() {
		return custom_product_id;
	}

	public void setCustom_product_id(long custom_product_id) {
		this.custom_product_id = custom_product_id;
	}

	public long getCustom_item_id() {
		return custom_item_id;
	}

	public void setCustom_item_id(long custom_item_id) {
		this.custom_item_id = custom_item_id;
	}

	public List<ProductLayoutRequest> getProduct_layout() {
		return product_layout;
	}

	public void setProduct_layout(List<ProductLayoutRequest> product_layout) {
		this.product_layout = product_layout;
	}

	public List<ProductSizeRequest> getProduct_sizes() {
		return product_sizes;
	}

	public void setProduct_sizes(List<ProductSizeRequest> product_sizes) {
		this.product_sizes = product_sizes;
	}

}
