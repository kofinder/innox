package com.finder.innox.repository;

import java.util.List;

import com.finder.innox.core.domain.User;

public interface UserDao extends GenericDao<User, Long> {

	List<User> getAllUser();

	User findByUserName(String userName, int userRole);

	User findByEmail(String email);

	List<User> getDesignerList();

	User findByPhoneNo(String phoneNo, long userId);

	User isUserNameAlreadyExist(String userName, int userRole, long userId);

	List<User> getActiveUserForAnnouncement();

	void updateDeviceToken(long userId, String deviceToken, int deviceType);

}
