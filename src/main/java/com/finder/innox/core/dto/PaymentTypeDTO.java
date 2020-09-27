package com.finder.innox.core.dto;

import java.io.Serializable;

import com.finder.innox.core.domain.PaymentType;
import com.finder.innox.utils.CommonStatus;

public class PaymentTypeDTO implements Serializable {

	private static final long serialVersionUID = 7592097265741127171L;

	private long seq;

	private String name;

	private String paymentTypeImage;

	private int status;

	private int isOffline;

	private String code;

	public PaymentTypeDTO() {
		super();
	}

	public PaymentTypeDTO(PaymentType paymentType) {
		if (paymentType != null) {
			this.seq = paymentType.getSeq();
			this.name = paymentType.getName();
			this.code = paymentType.getCode();
			this.paymentTypeImage = paymentType.getPaymentTypeImage();
			this.status = paymentType.getStatus() == null ? CommonStatus.ACTIVE.getCode() : paymentType.getStatus();
			this.isOffline = paymentType.getIsOffline();
		}
	}

	public long getSeq() {
		return seq;
	}

	public void setSeq(long seq) {
		this.seq = seq;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPaymentTypeImage() {
		return paymentTypeImage;
	}

	public void setPaymentTypeImage(String paymentTypeImage) {
		this.paymentTypeImage = paymentTypeImage;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getIsOffline() {
		return isOffline;
	}

	public void setIsOffline(int isOffline) {
		this.isOffline = isOffline;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
