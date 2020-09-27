package com.finder.innox.request;

import java.io.Serializable;

public class ProductArtworkRequest implements Serializable {

	private static final long serialVersionUID = 3501661382478444511L;

	private long artwork_id;

	public long getArtwork_id() {
		return artwork_id;
	}

	public void setArtwork_id(long artwork_id) {
		this.artwork_id = artwork_id;
	}

}
