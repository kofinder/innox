package com.finder.innox.repository;

import com.finder.innox.core.domain.CustomItemSize;

public interface CustomItemSizeDao extends GenericDao<CustomItemSize, Long>{
	
	void deleteDataByCustomItemId(long customIemId);

}
