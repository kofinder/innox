package com.finder.ecoshop.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HomePageResponse implements Serializable {

	private static final long serialVersionUID = 8587602261745781473L;

	private List<BannerResponse> banner_list = new ArrayList<BannerResponse>();

	public List<BannerResponse> getBanner_list() {
		return banner_list;
	}

	public void setBanner_list(List<BannerResponse> banner_list) {
		this.banner_list = banner_list;
	}

}
