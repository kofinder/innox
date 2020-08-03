package com.finder.ecoshop.core.services;

import java.util.List;

import com.finder.ecoshop.core.dto.CustomItemLayoutDTO;

public interface CustomItemLayoutService {

	List<CustomItemLayoutDTO> getCustomItemLayoutListByItemId(long customItemId);
	
	CustomItemLayoutDTO manageCustomItemLayout(CustomItemLayoutDTO customItemLayoutDTO)throws Exception;
	
	int isValidLayoutName(long customItemId, String layoutName, long seq);
	
	CustomItemLayoutDTO getCustomItemLayoutbyId(long customLayoutId);

}
