package com.finder.ecoshop.repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.finder.ecoshop.core.domain.AdminUser;
import com.finder.ecoshop.repository.UserDao;

@SuppressWarnings("deprecation")
@Repository
public class UserDaoImpl extends GenericDaoImpl<AdminUser, Integer> implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<AdminUser> getAllUser() {
		Criteria c  = this.getCurrentSession().createCriteria(AdminUser.class);
		c.add(Restrictions.ne("userSeq", 0));
		return c.list();
	}

	@Override
	public AdminUser findByUserName(String userName) {
		Criteria c  = this.sessionFactory.getCurrentSession().createCriteria(AdminUser.class);
		c.add(Restrictions.ne("userName", userName));
		return (AdminUser) c.uniqueResult();
	}

	@Override
	public AdminUser findByEmail(String email) {
		Criteria c  = this.sessionFactory.getCurrentSession().createCriteria(AdminUser.class);
		c.add(Restrictions.ne("email", email));
		return (AdminUser) c.uniqueResult();
	}

}
