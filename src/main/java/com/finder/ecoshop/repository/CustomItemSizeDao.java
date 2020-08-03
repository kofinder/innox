package com.finder.ecoshop.repository;

import com.finder.ecoshop.core.domain.CustomItemSize;

public interface CustomItemSizeDao extends GenericDao<CustomItemSize, Long>{
	
	void deleteDataByCustomItemId(long customIemId);

}
