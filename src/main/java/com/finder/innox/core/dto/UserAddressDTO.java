package com.finder.innox.core.dto;

import java.io.Serializable;

import com.finder.innox.core.domain.UserAddress;

public class UserAddressDTO implements Serializable {

	private static final long serialVersionUID = 3934574918743453638L;

	private long seq;

	private StateDTO stateDTO;

	private TownshipDTO townshipDTO;

	private String detailAddress;

	private boolean isPrimaryAddress;

	private UserDTO userDTO;

	public UserAddressDTO() {
		super();
	}

	public UserAddressDTO(UserAddress address) {
		if (address != null) {
			this.seq = address.getSeq();
			this.stateDTO = new StateDTO(address.getState());
			this.townshipDTO = new TownshipDTO(address.getTownship());
			this.detailAddress = address.getDetailAddress();
			this.isPrimaryAddress = address.getIsPrimaryAddress() == null ? true : address.getIsPrimaryAddress();
			this.userDTO = new UserDTO(address.getUser());
		}
	}

	public long getSeq() {
		return seq;
	}

	public void setSeq(long seq) {
		this.seq = seq;
	}

	public StateDTO getStateDTO() {
		return stateDTO;
	}

	public void setStateDTO(StateDTO stateDTO) {
		this.stateDTO = stateDTO;
	}

	public TownshipDTO getTownshipDTO() {
		return townshipDTO;
	}

	public void setTownshipDTO(TownshipDTO townshipDTO) {
		this.townshipDTO = townshipDTO;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public boolean isPrimaryAddress() {
		return isPrimaryAddress;
	}

	public void setPrimaryAddress(boolean isPrimaryAddress) {
		this.isPrimaryAddress = isPrimaryAddress;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

}
