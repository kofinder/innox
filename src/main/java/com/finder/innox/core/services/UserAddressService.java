package com.finder.innox.core.services;

import com.finder.innox.core.dto.UserAddressDTO;

public interface UserAddressService {

	UserAddressDTO getUserAddressByUserId(long userId);
}
