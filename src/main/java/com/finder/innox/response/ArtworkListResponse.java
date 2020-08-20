package com.finder.innox.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ArtworkListResponse implements Serializable {

	private static final long serialVersionUID = 6489840573061105030L;

	private List<ArtworkResponse> artworks = new ArrayList<ArtworkResponse>();

	public List<ArtworkResponse> getArtworks() {
		return artworks;
	}

	public void setArtworks(List<ArtworkResponse> artworks) {
		this.artworks = artworks;
	}

}
