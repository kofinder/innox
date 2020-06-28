package com.finder.ecoshop.core.services.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finder.ecoshop.core.domain.AdminUser;
import com.finder.ecoshop.core.domain.Role;
import com.finder.ecoshop.core.dto.UserDTO;
import com.finder.ecoshop.core.services.UserService;
import com.finder.ecoshop.repository.UserDao;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public List<AdminUser> getAllUser() {
		return userDao.getAllUser();
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AdminUser user = userDao.findByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}

		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		grantedAuthorities.add(new SimpleGrantedAuthority("USER"));
		grantedAuthorities.add(new SimpleGrantedAuthority("ADMIN"));

		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				grantedAuthorities);

	}

	@Override
	public AdminUser findByEmail(String email) {
		return userDao.findByEmail(email);
	}

	@Override
	public void save(UserDTO dto) {
		AdminUser user = new AdminUser();
		user.setUserName(dto.getUserName());
		user.setEmail(dto.getEmail());
		user.setPassword(passwordEncoder.encode(dto.getPassword()));
		
        user.setRoles(Arrays.asList(
                new Role("ROLE_USER"),
                new Role("ROLE_ADMIN")));

		userDao.save(user);

	}

}
