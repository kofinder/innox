package com.finder.ecoshop.core.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.finder.ecoshop.core.domain.User;
import com.finder.ecoshop.core.dto.UserDTO;

public interface UserService extends UserDetailsService {
	List<User> getAllUser();

	User findByEmail(String email);

	void save(UserDTO dto);
}
