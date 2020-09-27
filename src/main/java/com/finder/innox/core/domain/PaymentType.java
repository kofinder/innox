package com.finder.innox.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "payment_type")
public class PaymentType extends CommonEntity implements Serializable {

	private static final long serialVersionUID = -3617366959553584398L;

	@Column(name = "name")
	private String name;

	@Column(name = "code")
	private String code;

	@Column(name = "payment_type_image")
	private String paymentTypeImage;

	@Column(name = "status")
	private Integer status;

	@Column(name = "is_offline")
	private Integer isOffline;

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

	public String getPaymentTypeImage() {
		return paymentTypeImage;
	}

	public void setPaymentTypeImage(String paymentTypeImage) {
		this.paymentTypeImage = paymentTypeImage;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsOffline() {
		return isOffline;
	}

	public void setIsOffline(Integer isOffline) {
		this.isOffline = isOffline;
	}

}
