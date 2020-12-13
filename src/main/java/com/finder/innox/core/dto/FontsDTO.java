package com.finder.innox.core.dto;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import com.finder.innox.core.domain.Fonts;
import com.finder.innox.utils.CommonStatus;

public class FontsDTO implements Serializable {

	private static final long serialVersionUID = 7500846789929709763L;

	private long seq;

	private String name;

	private String fontURL;

	private String fontSample;

	private String fontDescription;

	private String fontImage;

	private MultipartFile fontImageFile;

	private int status;

	private String statusDesc;

	public FontsDTO() {
		super();
	}

	public FontsDTO(Fonts font) {
		if (font != null) {
			this.seq = font.getSeq();
			this.name = font.getName();
			this.fontURL = font.getFontURL();
			this.fontSample = font.getFontSample();
			this.fontDescription = font.getFontDescription();
			this.fontImage = font.getFontImage();
			this.status = font.getStatus() == null ? CommonStatus.INACTIVE.getCode() : font.getStatus();
			this.statusDesc = CommonStatus.getDescByCode(this.status);
		}
	}

	public long getSeq() {
		return seq;
	}

	public void setSeq(long seq) {
		this.seq = seq;
	}

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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public MultipartFile getFontImageFile() {
		return fontImageFile;
	}

	public void setFontImageFile(MultipartFile fontImageFile) {
		this.fontImageFile = fontImageFile;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

}
