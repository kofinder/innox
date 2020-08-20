package com.finder.innox.response;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import com.finder.innox.core.dto.ArtworkDTO;
import com.finder.innox.utils.CommonConstant;
import com.finder.innox.utils.CommonUtil;

public class ArtworkResponse implements Serializable {

	private static final long serialVersionUID = -4184834837602054596L;

	private long artwork_id;

	private String artwork_name;

	private BigDecimal artwork_price;

	private String artwork_price_text;

	private String artwork_image;

	public ArtworkResponse() {
		super();
	}

	public ArtworkResponse(ArtworkDTO artworkDTO, HttpServletRequest request) {
		if (artworkDTO != null) {
			this.artwork_id = artworkDTO.getSeq();
			this.artwork_name = artworkDTO.getArtworkName();
			this.artwork_price = artworkDTO.getSellPrice();
			this.artwork_price_text = CommonUtil.formatBigDecimalAsCurrency(artworkDTO.getSellPrice(),
					CommonConstant.CURRENCY_CODE_KS);
			this.artwork_image = CommonUtil.prepareImagePath(artworkDTO.getArtworkImage(), request);
		}
	}

	public long getArtwork_id() {
		return artwork_id;
	}

	public void setArtwork_id(long artwork_id) {
		this.artwork_id = artwork_id;
	}

	public String getArtwork_name() {
		return artwork_name;
	}

	public void setArtwork_name(String artwork_name) {
		this.artwork_name = artwork_name;
	}

	public BigDecimal getArtwork_price() {
		return artwork_price;
	}

	public void setArtwork_price(BigDecimal artwork_price) {
		this.artwork_price = artwork_price;
	}

	public String getArtwork_price_text() {
		return artwork_price_text;
	}

	public void setArtwork_price_text(String artwork_price_text) {
		this.artwork_price_text = artwork_price_text;
	}

	public String getArtwork_image() {
		return artwork_image;
	}

	public void setArtwork_image(String artwork_image) {
		this.artwork_image = artwork_image;
	}

}
