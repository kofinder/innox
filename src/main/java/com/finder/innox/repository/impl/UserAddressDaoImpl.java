package com.finder.innox.repository.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.finder.innox.core.domain.UserAddress;
import com.finder.innox.repository.UserAddressDao;

@SuppressWarnings({ "deprecation" })
@Repository
public class UserAddressDaoImpl extends GenericDaoImpl<UserAddress, Long> implements UserAddressDao {

	@Override
	public UserAddress getUserAddressByUserId(long userId) {
		Criteria c = this.getCurrentSession().createCriteria(UserAddress.class);
		c.add(Restrictions.eq("user.userSeq", userId));
		c.setMaxResults(1);
		return (UserAddress) c.uniqueResult();
	}

}
