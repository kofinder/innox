package com.finder.innox.repository;

import java.util.List;

import com.finder.innox.core.domain.CustomItemLayout;

public interface CustomerItemLayoutDao extends GenericDao<CustomItemLayout, Long> {

	List<CustomItemLayout> getCustomItemLayoutListByItemId(long customItemId, int status);
	
	int isValidLayoutName(long customItemId, String layoutName, long seq);

}
