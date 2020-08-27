package com.finder.innox.core.dto;

import java.io.Serializable;

import com.finder.innox.core.domain.State;

public class StateDTO implements Serializable {

	private static final long serialVersionUID = -2545740254731119011L;

	private long seq;

	private String name;

	private int stateNo;

	private UserDTO userDTO;

	public StateDTO() {
		super();
	}

	public StateDTO(State state) {
		if (state != null) {
			this.seq = state.getSeq();
			this.name = state.getName();
			this.stateNo = state.getStateNo() == null ? 0 : state.getStateNo();
			this.userDTO = new UserDTO(state.getUser());
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

	public int getStateNo() {
		return stateNo;
	}

	public void setStateNo(int stateNo) {
		this.stateNo = stateNo;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

}
