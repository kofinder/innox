package com.finder.ecoshop.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Township extends CommonEntity implements Serializable {

	private static final long serialVersionUID = 3862232551402479393L;

	@ManyToOne
	@JoinColumn(name = "state_id")
	private State state;

	@ManyToOne
	@JoinColumn(name = "city_id")
	private City city;

	@ManyToOne
	@JoinColumn(name = "zone_id")
	private Zone zone;
	
	@ManyToOne
	@JoinColumn(name = "created_by_id")
	private User user;

	@Column(name = "township_name")
	private String townshipName;

	@Column(name = "nrc_name")
	private String nrcName;
	
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Zone getZone() {
		return zone;
	}

	public void setZone(Zone zone) {
		this.zone = zone;
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
