package com.finder.ecoshop.response;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import com.finder.ecoshop.core.dto.BannerDTO;
import com.finder.ecoshop.utils.CommonUtil;

public class BannerResponse implements Serializable {

	private static final long serialVersionUID = -5856358820315193782L;

	private long banner_id;

	private String banner_name;

	private String image_path;

	public BannerResponse() {
		super();
	}

	public BannerResponse(BannerDTO banner, HttpServletRequest request) {
		this.banner_id = banner.getSeq();
		this.banner_name = banner.getName();
		this.image_path = CommonUtil.prepareImagePath(banner.getImagePath(), request);
	}

	public long getBanner_id() {
		return banner_id;
	}

	public void setBanner_id(long banner_id) {
		this.banner_id = banner_id;
	}

	public String getBanner_name() {
		return banner_name;
	}

	public void setBanner_name(String banner_name) {
		this.banner_name = banner_name;
	}

	public String getImage_path() {
		return image_path;
	}

	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}

}
