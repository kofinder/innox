package com.finder.innox.repository;

import java.util.List;

import com.finder.innox.core.domain.User;


public interface UserDao extends GenericDao<User, Integer>{
	
	List<User> getAllUser();
	
	User findByUserName(String userName);
	
	User findByEmail(String email);
	
	List<User> getDesignerList();

}
