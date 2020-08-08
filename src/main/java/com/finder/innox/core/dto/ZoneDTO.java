package com.finder.innox.core.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.finder.innox.core.domain.Zone;

public class ZoneDTO implements Serializable {

	private static final long serialVersionUID = -4869702248071883724L;

	private long seq;

	private String zoneCode;

	private String zoneName;

	private BigDecimal deliveryFee;

	private String description;

	private boolean isRequiredAddress;

	public ZoneDTO() {
		super();
	}

	public ZoneDTO(Zone zone) {
		if (zone != null) {
			this.seq = zone.getSeq();
			this.zoneCode = zone.getZoneCode();
			this.zoneName = zone.getZoneName();
			this.deliveryFee = zone.getDeliveryFee() == null ? BigDecimal.ZERO : zone.getDeliveryFee();
			this.description = zone.getDescription();
			this.isRequiredAddress = zone.getIsRequiredAddress() == null ? false : zone.getIsRequiredAddress();
		}
	}

	public long getSeq() {
		return seq;
	}

	public void setSeq(long seq) {
		this.seq = seq;
	}

	public String getZoneCode() {
		return zoneCode;
	}

	public void setZoneCode(String zoneCode) {
		this.zoneCode = zoneCode;
	}

	public String getZoneName() {
		return zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

	public BigDecimal getDeliveryFee() {
		return deliveryFee;
	}

	public void setDeliveryFee(BigDecimal deliveryFee) {
		this.deliveryFee = deliveryFee;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isRequiredAddress() {
		return isRequiredAddress;
	}

	public void setRequiredAddress(boolean isRequiredAddress) {
		this.isRequiredAddress = isRequiredAddress;
	}

}
