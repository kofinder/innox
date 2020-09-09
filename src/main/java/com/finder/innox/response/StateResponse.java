package com.finder.innox.response;

import java.io.Serializable;

import com.finder.innox.core.dto.StateDTO;

public class StateResponse implements Serializable {

	private static final long serialVersionUID = 6762762031049177754L;

	private Long state_id;

	private String state_name;

	public StateResponse() {
		super();
	}

	public StateResponse(StateDTO state) {
		if (state != null) {
			this.state_id = state.getSeq();
			this.state_name = state.getName();
		}
	}

	public Long getState_id() {
		return state_id;
	}

	public void setState_id(Long state_id) {
		this.state_id = state_id;
	}

	public String getState_name() {
		return state_name;
	}

	public void setState_name(String state_name) {
		this.state_name = state_name;
	}

}
