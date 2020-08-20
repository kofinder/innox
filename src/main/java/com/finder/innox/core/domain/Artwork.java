package com.finder.innox.core.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "artwork")
public class Artwork extends CommonEntity implements Serializable {

	private static final long serialVersionUID = -3289344633680212857L;

	@ManyToOne
	@JoinColumn(name = "designer_id")
	private User designer;

	@Column(name = "artwork_name")
	private String artworkName;

	@Column(name = "original_price")
	private BigDecimal originalPrice;

	@Column(name = "sell_price")
	private BigDecimal sellPrice;

	@Column(name = "artwork_image")
	private String artworkImage;

	@Column(name = "status")
	private Integer status;

	public User getDesigner() {
		return designer;
	}

	public void setDesigner(User designer) {
		this.designer = designer;
	}

	public String getArtworkName() {
		return artworkName;
	}

	public void setArtworkName(String artworkName) {
		this.artworkName = artworkName;
	}

	public BigDecimal getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(BigDecimal originalPrice) {
		this.originalPrice = originalPrice;
	}

	public BigDecimal getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(BigDecimal sellPrice) {
		this.sellPrice = sellPrice;
	}

	public String getArtworkImage() {
		return artworkImage;
	}

	public void setArtworkImage(String artworkImage) {
		this.artworkImage = artworkImage;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
