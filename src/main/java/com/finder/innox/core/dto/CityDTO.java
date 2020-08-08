
package com.finder.innox.core.dto;

import java.io.Serializable;

import com.finder.innox.core.domain.City;

public class CityDTO implements Serializable {

	private static final long serialVersionUID = -7837281779075277001L;

	private long seq;

	private StateDTO stateDTO;

	private String cityName;

	public CityDTO() {
		super();
	}

	public CityDTO(City city) {
		if(city != null) {
			this.seq = city.getSeq();
			this.stateDTO = new StateDTO(city.getState());
			this.cityName = city.getCityName();
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

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

}
