package com.finder.innox.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DesignerListResponse implements Serializable {

	private static final long serialVersionUID = -5307486619558107296L;

	private List<DesignerResponse> artwork_designers = new ArrayList<DesignerResponse>();

	public List<DesignerResponse> getArtwork_designers() {
		return artwork_designers;
	}

	public void setArtwork_designers(List<DesignerResponse> artwork_designers) {
		this.artwork_designers = artwork_designers;
	}

}
