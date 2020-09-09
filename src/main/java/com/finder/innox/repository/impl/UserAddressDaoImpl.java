package com.finder.innox.repository.impl;

import org.springframework.stereotype.Repository;

import com.finder.innox.core.domain.UserAddress;
import com.finder.innox.repository.UserAddressDao;

@Repository
public class UserAddressDaoImpl extends GenericDaoImpl<UserAddress, Long> implements UserAddressDao {

}
