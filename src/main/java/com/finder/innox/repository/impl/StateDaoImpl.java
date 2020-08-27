package com.finder.innox.repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.finder.innox.core.domain.State;
import com.finder.innox.repository.StateDao;

@SuppressWarnings({ "unchecked", "deprecation" })
@Repository
public class StateDaoImpl extends GenericDaoImpl<State, Long> implements StateDao {

	@Override
	public State getStateDataById(long stateId) {
		Criteria c = this.getCurrentSession().createCriteria(State.class);
		return (State) c.uniqueResult();
	}

	@Override
	public List<State> getStateDataList() {
		Criteria c = this.getCurrentSession().createCriteria(State.class);
		return c.list();
	}

}
