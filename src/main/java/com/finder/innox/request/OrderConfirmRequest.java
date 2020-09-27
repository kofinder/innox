package com.finder.innox.request;

import java.io.Serializable;
import java.util.List;

public class OrderConfirmRequest implements Serializable {

	private static final long serialVersionUID = 5045535481664287268L;

	private List<Long> cart_ids;

	private String paymen_type_code;

	private Long state_id;

	private Long township_id;

	private String detail_address;

	private String remark;

	public List<Long> getCart_ids() {
		return cart_ids;
	}

	public void setCart_ids(List<Long> cart_ids) {
		this.cart_ids = cart_ids;
	}

	public String getPaymen_type_code() {
		return paymen_type_code;
	}

	public void setPaymen_type_code(String paymen_type_code) {
		this.paymen_type_code = paymen_type_code;
	}

	public Long getState_id() {
		return state_id;
	}

	public void setState_id(Long state_id) {
		this.state_id = state_id;
	}

	public Long getTownship_id() {
		return township_id;
	}

	public void setTownship_id(Long township_id) {
		this.township_id = township_id;
	}

	public String getDetail_address() {
		return detail_address;
	}

	public void setDetail_address(String detail_address) {
		this.detail_address = detail_address;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
