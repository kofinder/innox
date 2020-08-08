package com.finder.innox.core.domain;

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
@Table(name = "state")
public class State extends CommonEntity implements Serializable {

	private static final long serialVersionUID = -1528024845230715560L;

	@Column(name = "name")
	private String name;

	@Column(name = "state_no")
	private Integer stateNo;

	@ManyToOne
	@JoinColumn(name = "created_by_id")
	private User user;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStateNo() {
		return stateNo;
	}

	public void setStateNo(Integer stateNo) {
		this.stateNo = stateNo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
