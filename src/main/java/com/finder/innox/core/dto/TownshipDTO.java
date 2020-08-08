package com.finder.innox.core.dto;

import java.io.Serializable;

import com.finder.innox.core.domain.Township;

public class TownshipDTO implements Serializable {

	private static final long serialVersionUID = -2404286466042576369L;

	private long seq;

	private StateDTO stateDTO;

	private CityDTO cityDTO;

	private ZoneDTO zoneDTO;

	private UserDTO userDTO;

	private String townshipName;

	private String nrcName;

	public TownshipDTO() {
		super();
	}

	public TownshipDTO(Township township) {
		if (township != null) {
			this.seq = township.getSeq();
			this.stateDTO = new StateDTO(township.getState());
			this.cityDTO = new CityDTO(township.getCity());
			this.zoneDTO = new ZoneDTO(township.getZone());
			this.townshipName = township.getTownshipName();
			this.nrcName = township.getNrcName();
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

	public CityDTO getCityDTO() {
		return cityDTO;
	}

	public void setCityDTO(CityDTO cityDTO) {
		this.cityDTO = cityDTO;
	}

	public ZoneDTO getZoneDTO() {
		return zoneDTO;
	}

	public void setZoneDTO(ZoneDTO zoneDTO) {
		this.zoneDTO = zoneDTO;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public String getTownshipName() {
		return townshipName;
	}

	public void setTownshipName(String townshipName) {
		this.townshipName = townshipName;
	}

	public String getNrcName() {
		return nrcName;
	}

	public void setNrcName(String nrcName) {
		this.nrcName = nrcName;
	}

}
