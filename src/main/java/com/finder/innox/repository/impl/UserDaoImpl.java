package com.finder.innox.repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.finder.innox.core.domain.User;
import com.finder.innox.repository.UserDao;
import com.finder.innox.utils.CommonStatus;
import com.finder.innox.utils.UserRoleEnum;

@SuppressWarnings({ "deprecation", "unchecked" })
@Repository
public class UserDaoImpl extends GenericDaoImpl<User, Long> implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<User> getAllUser() {
		Criteria c = this.getCurrentSession().createCriteria(User.class);
		c.add(Restrictions.ne("userSeq", 0));
		return c.list();
	}

	@Override
	public User findByUserName(String userName, int userRole) {
		Criteria c = this.sessionFactory.getCurrentSession().createCriteria(User.class);
		c.add(Restrictions.eq("userName", userName));

		if (userRole > 0) {
			c.add(Restrictions.eq("userRoleLevel", userRole));
		}
		c.setMaxResults(1);

		Object usr = c.uniqueResult();
		return (User) usr;
	}

	@Override
	public User findByEmail(String email) {
		Criteria c = this.sessionFactory.getCurrentSession().createCriteria(User.class);
		c.add(Restrictions.ne("email", email));
		return (User) c.uniqueResult();
	}

	@Override
	public List<User> getDesignerList() {
		Criteria c = this.getCurrentSession().createCriteria(User.class);
		c.add(Restrictions.eq("userRoleLevel", UserRoleEnum.ROLE_DESIGNER.getCode()));
		c.add(Restrictions.eq("status", CommonStatus.ACTIVE.getCode()));
		return c.list();
	}

	@Override
	public User findByPhoneNo(String phoneNo) {
		Criteria c = this.getCurrentSession().createCriteria(User.class);
		c.add(Restrictions.eq("phoneNo", phoneNo));
		return (User) c.uniqueResult();
	}

	@Override
	public User isUserNameAlreadyExist(String userName, int userRole, long userId) {
		Criteria c = this.sessionFactory.getCurrentSession().createCriteria(User.class);
		c.add(Restrictions.eq("userName", userName));

		if (userRole > 0) {
			c.add(Restrictions.eq("userRoleLevel", userRole));
		}

		if (userId > 0) {
			c.add(Restrictions.ne("userSeq", userId));
		}

		c.setMaxResults(1);

		Object usr = c.uniqueResult();
		return (User) usr;
	}

}
