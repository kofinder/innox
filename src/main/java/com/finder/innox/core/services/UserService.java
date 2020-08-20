package com.finder.innox.core.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.finder.innox.core.domain.User;
import com.finder.innox.core.dto.UserDTO;

public interface UserService extends UserDetailsService {

	List<User> getAllUser();

	User findByEmail(String email);

	void save(UserDTO dto);

	List<UserDTO> getDesignerList();

	UserDTO findByName(String userName);

}
