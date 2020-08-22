package com.finder.innox.core.services;

import java.util.List;

import com.finder.innox.core.dto.CustomItemLayoutDTO;

public interface CustomItemLayoutService {

	List<CustomItemLayoutDTO> getCustomItemLayoutListByItemId(long customItemId, int status);
	
	CustomItemLayoutDTO manageCustomItemLayout(CustomItemLayoutDTO customItemLayoutDTO)throws Exception;
	
	int isValidLayoutName(long customItemId, String layoutName, long seq);
	
	CustomItemLayoutDTO getCustomItemLayoutbyId(long customLayoutId);

}
