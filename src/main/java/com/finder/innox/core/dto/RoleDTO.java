package com.finder.innox.core.dto;

import java.io.Serializable;

import com.finder.innox.core.domain.Role;

public class RoleDTO implements Serializable {

	private static final long serialVersionUID = 8805082850247864958L;

	private long id;

	private String name;

	public RoleDTO() {
		super();
	}

	public RoleDTO(Role role) {
		if (role != null) {
			this.id = role.getId();
			this.name = role.getName();
		}
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
