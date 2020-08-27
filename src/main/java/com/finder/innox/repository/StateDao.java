package com.finder.innox.repository;

import java.util.List;

import com.finder.innox.core.domain.State;

public interface StateDao extends GenericDao<State, Long>{
	
	State getStateDataById(long stateId);
	
	List<State> getStateDataList();

}
