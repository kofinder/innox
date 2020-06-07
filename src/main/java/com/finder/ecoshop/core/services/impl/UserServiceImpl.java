package com.finder.ecoshop.core.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finder.ecoshop.core.domain.AdminUser;
import com.finder.ecoshop.core.services.UserService;
import com.finder.ecoshop.repository.UserDao;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;

	@Override
	public List<AdminUser> getAllUser() {
		return userDao.getAllUser();
	}

}
