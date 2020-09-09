package com.finder.innox.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TownshipListResponse implements Serializable {

	private static final long serialVersionUID = 2779223702662346919L;

	private List<TownshipResponse> township_list = new ArrayList<TownshipResponse>();

	public List<TownshipResponse> getTownship_list() {
		return township_list;
	}

	public void setTownship_list(List<TownshipResponse> township_list) {
		this.township_list = township_list;
	}

}
