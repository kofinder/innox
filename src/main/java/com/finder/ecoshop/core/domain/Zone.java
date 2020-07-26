package com.finder.ecoshop.core.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "zone")
public class Zone extends CommonEntity implements Serializable {

	private static final long serialVersionUID = -8283896847309999234L;

	@Column(name = "zone_code")
	private String zoneCode;

	@Column(name = "zone_name")
	private String zoneName;

	@Column(name = "delivery_fee")
	private BigDecimal deliveryFee;

	@Column(name = "description")
	private String description;

	@Column(name = "is_required_address")
	private Boolean isRequiredAddress;

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

	public Boolean getIsRequiredAddress() {
		return isRequiredAddress;
	}

	public void setIsRequiredAddress(Boolean isRequiredAddress) {
		this.isRequiredAddress = isRequiredAddress;
	}

}
