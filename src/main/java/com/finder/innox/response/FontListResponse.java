package com.finder.innox.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FontListResponse implements Serializable {

	private static final long serialVersionUID = 4901169649283277182L;

	private List<FontResponse> fonts = new ArrayList<FontResponse>();

	public List<FontResponse> getFonts() {
		return fonts;
	}

	public void setFonts(List<FontResponse> fonts) {
		this.fonts = fonts;
	}

}
