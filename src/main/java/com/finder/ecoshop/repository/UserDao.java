package com.finder.ecoshop.repository;

import java.util.List;

import com.finder.ecoshop.core.domain.AdminUser;


public interface UserDao extends GenericDao<AdminUser, Integer>{
	
	List<AdminUser> getAllUser();
	
	AdminUser findByUserName(String userName);
	
	AdminUser findByEmail(String email);

}
