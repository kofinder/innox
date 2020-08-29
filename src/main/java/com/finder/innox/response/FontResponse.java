package com.finder.innox.response;

import java.io.Serializable;

public class FontResponse implements Serializable {

	private static final long serialVersionUID = -4498529513522920610L;

	private long font_id;

	private String font_url;

	private String font_sample;

	public long getFont_id() {
		return font_id;
	}

	public void setFont_id(long font_id) {
		this.font_id = font_id;
	}

	public String getFont_url() {
		return font_url;
	}

	public void setFont_url(String font_url) {
		this.font_url = font_url;
	}

	public String getFont_sample() {
		return font_sample;
	}

	public void setFont_sample(String font_sample) {
		this.font_sample = font_sample;
	}

}
