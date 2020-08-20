package com.finder.innox.core.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finder.innox.core.domain.Role;
import com.finder.innox.core.domain.User;
import com.finder.innox.core.dto.UserDTO;
import com.finder.innox.core.services.UserService;
import com.finder.innox.repository.UserDao;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public List<User> getAllUser() {
		return userDao.getAllUser();
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));

	}

	@Override
	public User findByEmail(String email) {
		return userDao.findByEmail(email);
	}

	@Override
	public void save(UserDTO dto) {
		User user = new User();
		user.setUserName(dto.getUserName());
		user.setEmail(dto.getEmail());
		user.setPassword(passwordEncoder.encode(dto.getPassword()));

		user.setRoles(Arrays.asList(new Role("ROLE_ADMIN")));

		userDao.save(user);

	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	@Override
	public List<UserDTO> getDesignerList() {
		List<User> entityList = userDao.getDesignerList();
		if (entityList == null || entityList.isEmpty()) {
			return new ArrayList<UserDTO>();
		}

		List<UserDTO> dtoList = new ArrayList<UserDTO>();
		entityList.forEach(user -> {
			UserDTO userDto = new UserDTO(user);
			dtoList.add(userDto);
		});
		return dtoList;
	}

	@Override
	public UserDTO findByName(String userName) {
		User user = userDao.findByUserName(userName);
		if (user == null) {
			throw new UsernameNotFoundException(userName);
		}
		return new UserDTO(user);
	}

}
