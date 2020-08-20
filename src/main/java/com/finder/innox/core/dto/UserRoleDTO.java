package com.finder.innox.core.dto;

import java.io.Serializable;

import com.finder.innox.core.domain.UserRole;

public class UserRoleDTO implements Serializable {

	private static final long serialVersionUID = -5512120451569475046L;

	private RoleDTO roleDTO;

	private UserDTO userDTO;

	public UserRoleDTO() {
		super();
	}

	public UserRoleDTO(UserRole userRole) {
		if (userRole != null) {
			this.roleDTO = new RoleDTO(userRole.getRole());
			this.userDTO = new UserDTO(userRole.getUser());
		}
	}

	public RoleDTO getRoleDTO() {
		return roleDTO;
	}

	public void setRoleDTO(RoleDTO roleDTO) {
		this.roleDTO = roleDTO;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

}
