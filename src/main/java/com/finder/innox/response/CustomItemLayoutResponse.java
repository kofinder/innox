package com.finder.innox.response;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import com.finder.innox.core.dto.CustomItemLayoutDTO;
import com.finder.innox.utils.CommonConstant;
import com.finder.innox.utils.CommonUtil;

public class CustomItemLayoutResponse implements Serializable {

	private static final long serialVersionUID = 9161016225637635218L;

	private long custom_item_layout_id;

	private String layout_name;

	private BigDecimal layout_price;

	private String layout_price_text;

	private String layout_image;

	private int sequence_no;

	public CustomItemLayoutResponse() {
		super();
	}

	public CustomItemLayoutResponse(CustomItemLayoutDTO itemLayout, HttpServletRequest request) {
		if (itemLayout != null) {
			this.custom_item_layout_id = itemLayout.getSeq();
			this.layout_name = itemLayout.getLayoutName();
			this.layout_price = itemLayout.getLayoutPrice();
			this.layout_price_text = CommonUtil.formatBigDecimalAsCurrency(itemLayout.getLayoutPrice(),
					CommonConstant.CURRENCY_CODE_KS);
			this.sequence_no = itemLayout.getSequenceNo();
			this.layout_image = CommonUtil.prepareImagePath(itemLayout.getLayoutImage(), request);
		}
	}

	public long getCustom_item_layout_id() {
		return custom_item_layout_id;
	}

	public void setCustom_item_layout_id(long custom_item_layout_id) {
		this.custom_item_layout_id = custom_item_layout_id;
	}

	public String getLayout_name() {
		return layout_name;
	}

	public void setLayout_name(String layout_name) {
		this.layout_name = layout_name;
	}

	public BigDecimal getLayout_price() {
		return layout_price;
	}

	public void setLayout_price(BigDecimal layout_price) {
		this.layout_price = layout_price;
	}

	public String getLayout_price_text() {
		return layout_price_text;
	}

	public void setLayout_price_text(String layout_price_text) {
		this.layout_price_text = layout_price_text;
	}

	public int getSequence_no() {
		return sequence_no;
	}

	public void setSequence_no(int sequence_no) {
		this.sequence_no = sequence_no;
	}

	public String getLayout_image() {
		return layout_image;
	}

	public void setLayout_image(String layout_image) {
		this.layout_image = layout_image;
	}

}
