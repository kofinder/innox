package com.finder.innox.response;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.finder.innox.core.dto.CustomItemDTO;
import com.finder.innox.utils.CommonConstant;
import com.finder.innox.utils.CommonUtil;

public class CustomItemResponse implements Serializable {

	private static final long serialVersionUID = 756095527288422513L;

	private long custom_item_id;

	private String item_name;

	private BigDecimal item_price;

	private String item_price_text;

	private int sequence_no;

	private long color_id;

	private String color_code;

	private String color_name;

	private List<SizeResponse> custom_sizes = new ArrayList<SizeResponse>();

	private List<CustomItemLayoutResponse> custom_item_layouts = new ArrayList<CustomItemLayoutResponse>();

	public CustomItemResponse() {
		super();
	}

	public CustomItemResponse(CustomItemDTO customItem) {
		if (customItem != null) {
			this.custom_item_id = customItem.getSeq();
			this.item_name = customItem.getItemName();
			this.item_price = customItem.getItemPrice();
			this.item_price_text = CommonUtil.formatBigDecimalAsCurrency(customItem.getItemPrice(),
					CommonConstant.CURRENCY_CODE_KS);
			this.sequence_no = customItem.getSequenceNo();
			this.color_id = customItem.getColorDTO().getSeq();
			this.color_code = customItem.getColorDTO().getColorCode();
			this.color_name = customItem.getColorDTO().getColorName();
		}
	}

	public long getCustom_item_id() {
		return custom_item_id;
	}

	public void setCustom_item_id(long custom_item_id) {
		this.custom_item_id = custom_item_id;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public BigDecimal getItem_price() {
		return item_price;
	}

	public void setItem_price(BigDecimal item_price) {
		this.item_price = item_price;
	}

	public String getItem_price_text() {
		return item_price_text;
	}

	public void setItem_price_text(String item_price_text) {
		this.item_price_text = item_price_text;
	}

	public int getSequence_no() {
		return sequence_no;
	}

	public void setSequence_no(int sequence_no) {
		this.sequence_no = sequence_no;
	}

	public long getColor_id() {
		return color_id;
	}

	public void setColor_id(long color_id) {
		this.color_id = color_id;
	}

	public String getColor_code() {
		return color_code;
	}

	public void setColor_code(String color_code) {
		this.color_code = color_code;
	}

	public String getColor_name() {
		return color_name;
	}

	public void setColor_name(String color_name) {
		this.color_name = color_name;
	}

	public List<CustomItemLayoutResponse> getCustom_item_layouts() {
		return custom_item_layouts;
	}

	public void setCustom_item_layouts(List<CustomItemLayoutResponse> custom_item_layouts) {
		this.custom_item_layouts = custom_item_layouts;
	}

	public List<SizeResponse> getCustom_sizes() {
		return custom_sizes;
	}

	public void setCustom_sizes(List<SizeResponse> custom_sizes) {
		this.custom_sizes = custom_sizes;
	}

}
