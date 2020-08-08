package com.finder.innox.repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.finder.innox.core.domain.User;
import com.finder.innox.repository.UserDao;

@SuppressWarnings("deprecation")
@Repository
public class UserDaoImpl extends GenericDaoImpl<User, Integer> implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUser() {
		Criteria c  = this.getCurrentSession().createCriteria(User.class);
		c.add(Restrictions.ne("userSeq", 0));
		return c.list();
	}

	@Override
	public User findByUserName(String userName) {
		Criteria c  = this.sessionFactory.getCurrentSession().createCriteria(User.class);
		c.add(Restrictions.eq("userName", userName));
		c.setMaxResults(1);

		Object usr = c.uniqueResult();
		return (User) usr;
	}

	@Override
	public User findByEmail(String email) {
		Criteria c  = this.sessionFactory.getCurrentSession().createCriteria(User.class);
		c.add(Restrictions.ne("email", email));
		return (User) c.uniqueResult();
	}

}
