package com.finder.innox.request;

import java.io.Serializable;

public class ProductFontRequest implements Serializable {

	private static final long serialVersionUID = 8180220327576576372L;

	private long font_id;

	public long getFont_id() {
		return font_id;
	}

	public void setFont_id(long font_id) {
		this.font_id = font_id;
	}

}
