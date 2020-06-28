package com.finder.ecoshop.core.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.finder.ecoshop.core.domain.AdminUser;
import com.finder.ecoshop.core.dto.UserDTO;

public interface UserService extends UserDetailsService {
	List<AdminUser> getAllUser();

	AdminUser findByEmail(String email);

	void save(UserDTO dto);
}
