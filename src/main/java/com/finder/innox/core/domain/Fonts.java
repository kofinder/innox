
package com.finder.innox.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "fonts")
public class Fonts extends CommonEntity implements Serializable {

	private static final long serialVersionUID = 9126702505196763367L;

	@Column(name = "name")
	private String name;

	@Column(name = "font_url")
	private String fontURL;

	@Column(name = "font_sample")
	private String fontSample;

	@Column(name = "font_description")
	private String fontDescription;

	@Column(name = "font_image")
	private String fontImage;

	@Column(name = "status")
	private Integer status;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFontURL() {
		return fontURL;
	}

	public void setFontURL(String fontURL) {
		this.fontURL = fontURL;
	}

	public String getFontSample() {
		return fontSample;
	}

	public void setFontSample(String fontSample) {
		this.fontSample = fontSample;
	}

	public String getFontDescription() {
		return fontDescription;
	}

	public void setFontDescription(String fontDescription) {
		this.fontDescription = fontDescription;
	}

	public String getFontImage() {
		return fontImage;
	}

	public void setFontImage(String fontImage) {
		this.fontImage = fontImage;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
