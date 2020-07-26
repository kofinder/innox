package com.finder.ecoshop.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author Zay Maung Maung Myint
 *
 */
@Entity
@Table(name = "city")
public class City extends CommonEntity implements Serializable {

	private static final long serialVersionUID = 1781261409213290108L;

	@ManyToOne
	@JoinColumn(name = "state_id")
	private State state;

	@Column(name = "city_name")
	private String cityName;

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

}
