package com.finder.innox.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "artwork_tags")
public class ArtworkTag extends CommonEntity implements Serializable {

	private static final long serialVersionUID = -6826464022851024257L;

	@ManyToOne
	@JoinColumn(name = "artwork_id")
	private Artwork artwork;

	@ManyToOne
	@JoinColumn(name = "artwork_category_id")
	private ArtworkCategory artworkCategory;

	@Column(name = "status")
	private Integer status;

	public Artwork getArtwork() {
		return artwork;
	}

	public void setArtwork(Artwork artwork) {
		this.artwork = artwork;
	}

	public ArtworkCategory getArtworkCategory() {
		return artworkCategory;
	}

	public void setArtworkCategory(ArtworkCategory artworkCategory) {
		this.artworkCategory = artworkCategory;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
