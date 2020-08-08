package com.finder.innox.repository.impl;

import org.springframework.stereotype.Repository;

import com.finder.innox.core.domain.State;
import com.finder.innox.repository.StateDao;

@Repository
public class StateDaoImpl extends GenericDaoImpl<State, Long> implements StateDao{

}
