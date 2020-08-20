package com.finder.innox.core.dto;

import java.io.Serializable;

import com.finder.innox.core.domain.ArtworkTag;
import com.finder.innox.utils.CommonStatus;

public class ArtworkTagDTO implements Serializable {

	private static final long serialVersionUID = 7757993552035234085L;

	private ArtworkDTO artworkDTO;

	private ArtworkCategoryDTO artworkCategoryDTO;

	private int status;

	public ArtworkTagDTO() {
		super();
	}

	public ArtworkTagDTO(ArtworkTag artworkTag) {
		if (artworkTag != null) {
			this.artworkDTO = new ArtworkDTO(artworkTag.getArtwork());
			this.artworkCategoryDTO = new ArtworkCategoryDTO(artworkTag.getArtworkCategory());
			this.status = artworkTag.getStatus() == null ? CommonStatus.ACTIVE.getCode() : artworkTag.getStatus();
		}
	}

	public ArtworkDTO getArtworkDTO() {
		return artworkDTO;
	}

	public void setArtworkDTO(ArtworkDTO artworkDTO) {
		this.artworkDTO = artworkDTO;
	}

	public ArtworkCategoryDTO getArtworkCategoryDTO() {
		return artworkCategoryDTO;
	}

	public void setArtworkCategoryDTO(ArtworkCategoryDTO artworkCategoryDTO) {
		this.artworkCategoryDTO = artworkCategoryDTO;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
