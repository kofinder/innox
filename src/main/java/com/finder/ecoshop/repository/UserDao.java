package com.finder.ecoshop.repository;

import java.util.List;

import com.finder.ecoshop.core.domain.User;


public interface UserDao extends GenericDao<User, Integer>{
	
	List<User> getAllUser();
	
	User findByUserName(String userName);
	
	User findByEmail(String email);

}
