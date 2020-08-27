package com.finder.innox.core.services;

import java.util.List;

import com.finder.innox.core.dto.StateDTO;

public interface StateService {
	
	StateDTO getStateDataById(long stateId);
	
	List<StateDTO> getStateDataList();
	
	StateDTO stateManage(StateDTO stateDTO, String authUserName) throws Exception;

}
