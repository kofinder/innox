package com.finder.innox.repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.finder.innox.core.domain.State;
import com.finder.innox.repository.StateDao;

@SuppressWarnings({ "unchecked", "deprecation" })
@Repository
public class StateDaoImpl extends GenericDaoImpl<State, Long> implements StateDao {

	@Override
	public State getStateDataById(long stateId) {
		Criteria c = this.getCurrentSession().createCriteria(State.class);
		c.add(Restrictions.eq("seq", stateId));
		return (State) c.uniqueResult();
	}

	@Override
	public List<State> getStateDataList(int status) {
		Criteria c = this.getCurrentSession().createCriteria(State.class);
		if (status > 0) {
			c.add(Restrictions.eq("status", status));
		}
		c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return c.list();
	}

}
