package com.finder.innox.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StateListResponse implements Serializable {

	private static final long serialVersionUID = -8919163714908966983L;

	private List<StateResponse> state_list = new ArrayList<StateResponse>();

	public List<StateResponse> getState_list() {
		return state_list;
	}

	public void setState_list(List<StateResponse> state_list) {
		this.state_list = state_list;
	}

}
