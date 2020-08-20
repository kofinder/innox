package com.finder.innox.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ArtworkCategoryListResponse implements Serializable {

	private static final long serialVersionUID = -9000149323903219403L;

	private List<ArtworkCategoryResponse> artwork_categorys = new ArrayList<ArtworkCategoryResponse>();

	public List<ArtworkCategoryResponse> getArtwork_categorys() {
		return artwork_categorys;
	}

	public void setArtwork_categorys(List<ArtworkCategoryResponse> artwork_categorys) {
		this.artwork_categorys = artwork_categorys;
	}

}
