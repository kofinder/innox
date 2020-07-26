package com.finder.ecoshop.repository;

import java.util.List;

import com.finder.ecoshop.core.domain.CustomItemLayout;

public interface CustomerItemLayoutDao extends GenericDao<CustomItemLayout, Long> {

	List<CustomItemLayout> getCustomItemLayoutListByItemId(long customItemId);
	
	int isValidLayoutName(long customItemId, String layoutName);

}
