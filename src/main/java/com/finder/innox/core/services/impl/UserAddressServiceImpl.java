package com.finder.innox.core.services.impl;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finder.innox.core.domain.UserAddress;
import com.finder.innox.core.dto.UserAddressDTO;
import com.finder.innox.core.services.UserAddressService;
import com.finder.innox.repository.UserAddressDao;

@Service
@Transactional
public class UserAddressServiceImpl implements UserAddressService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private UserAddressDao userAddressDao;

	public UserAddressServiceImpl() {
		super();
	}

	@Autowired
	public UserAddressServiceImpl(UserAddressDao userAddressDao) {
		this.userAddressDao = userAddressDao;
	}

	@Override
	public UserAddressDTO getUserAddressByUserId(long userId) {
		logger.info("getUserAddressByUserId() >> User Id : " + userId);
		UserAddress address = userAddressDao.getUserAddressByUserId(userId);
		if (address != null) {
			return new UserAddressDTO(address);
		}
		return null;
	}

}
