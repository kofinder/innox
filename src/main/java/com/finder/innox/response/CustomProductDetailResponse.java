package com.finder.innox.response;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.finder.innox.core.dto.CustomProductDTO;
import com.finder.innox.utils.CommonConstant;
import com.finder.innox.utils.CommonUtil;

public class CustomProductDetailResponse implements Serializable {

	private static final long serialVersionUID = -2722151283127005315L;

	private long custom_product_id;

	private String product_name;

	private BigDecimal initial_price;

	private String initial_price_text;

	List<CustomItemResponse> custom_items = new ArrayList<CustomItemResponse>();

	public CustomProductDetailResponse() {
		super();
	}

	public CustomProductDetailResponse(CustomProductDTO customProduct) {
		if (customProduct != null) {
			this.custom_product_id = customProduct.getSeq();
			this.product_name = customProduct.getProductName();
			this.initial_price = customProduct.getInitialPrice();
			this.initial_price_text = CommonUtil.formatBigDecimalAsCurrency(customProduct.getInitialPrice(),
					CommonConstant.CURRENCY_CODE_KS);
		}
	}

	public long getCustom_product_id() {
		return custom_product_id;
	}

	public void setCustom_product_id(long custom_product_id) {
		this.custom_product_id = custom_product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public BigDecimal getInitial_price() {
		return initial_price;
	}

	public void setInitial_price(BigDecimal initial_price) {
		this.initial_price = initial_price;
	}

	public String getInitial_price_text() {
		return initial_price_text;
	}

	public void setInitial_price_text(String initial_price_text) {
		this.initial_price_text = initial_price_text;
	}

	public List<CustomItemResponse> getCustom_items() {
		return custom_items;
	}

	public void setCustom_items(List<CustomItemResponse> custom_items) {
		this.custom_items = custom_items;
	}

}
