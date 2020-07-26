package com.finder.ecoshop.repository.impl;

import org.springframework.stereotype.Repository;

import com.finder.ecoshop.core.domain.State;
import com.finder.ecoshop.repository.StateDao;

@Repository
public class StateDaoImpl extends GenericDaoImpl<State, Long> implements StateDao{

}
