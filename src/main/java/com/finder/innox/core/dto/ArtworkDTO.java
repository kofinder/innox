package com.finder.innox.core.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.finder.innox.core.domain.Artwork;
import com.finder.innox.utils.CommonStatus;

public class ArtworkDTO implements Serializable {

	private static final long serialVersionUID = -3726026219040874242L;

	private long seq;

	private UserDTO designerDTO;

	private String artworkName;

	private BigDecimal originalPrice;

	private BigDecimal sellPrice;

	private String artworkImage;

	private List<Long> artworkTemplateIdList = new ArrayList<Long>();

	public ArtworkDTO() {
		super();
	}

	public ArtworkDTO(Artwork artwork) {
		if (artwork != null) {
			this.seq = artwork.getSeq();
			this.designerDTO = new UserDTO(artwork.getDesigner());
			this.artworkName = artwork.getArtworkName();
			this.originalPrice = artwork.getOriginalPrice() == null ? BigDecimal.ZERO : artwork.getOriginalPrice();
			this.sellPrice = artwork.getSellPrice() == null ? BigDecimal.ZERO : artwork.getSellPrice();
			this.status = artwork.getStatus() == null ? CommonStatus.ACTIVE.getCode() : artwork.getStatus();
			this.artworkImage = artwork.getArtworkImage();
		}
	}

	private int status;

	public long getSeq() {
		return seq;
	}

	public void setSeq(long seq) {
		this.seq = seq;
	}

	public UserDTO getDesignerDTO() {
		return designerDTO;
	}

	public void setDesignerDTO(UserDTO designerDTO) {
		this.designerDTO = designerDTO;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<Long> getArtworkTemplateIdList() {
		return artworkTemplateIdList;
	}

	public void setArtworkTemplateIdList(List<Long> artworkTemplateIdList) {
		this.artworkTemplateIdList = artworkTemplateIdList;
	}

}
