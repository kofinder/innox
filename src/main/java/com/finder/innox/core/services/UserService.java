package com.finder.innox.core.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.finder.innox.core.domain.User;
import com.finder.innox.core.dto.UserDTO;
import com.finder.innox.request.DeviceTokenUpdateRequest;
import com.finder.innox.request.UserRegisterRequest;

public interface UserService extends UserDetailsService {

	List<User> getAllUser();

	User findByEmail(String email);

	void save(UserDTO dto);

	List<UserDTO> getDesignerList();

	UserDTO findByName(String userName, int userRole);

	boolean isUserNameAlreadExist(String userName, int userRole, long userId);

	UserDTO findByPhoneNo(String phoneNo);

	UserDTO userRegister(UserRegisterRequest registerRequest) throws Exception;

	UserDTO getUserById(long userId);

	UserDTO userProfileUpdate(UserRegisterRequest updateRequest) throws Exception;

	int updateDeviceToken(DeviceTokenUpdateRequest request) throws Exception;

}
