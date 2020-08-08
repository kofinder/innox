package com.finder.innox.repository;

import java.util.List;

import com.finder.innox.core.domain.CustomItem;

public interface CustomItemDao extends GenericDao<CustomItem, Long> {

	List<CustomItem> getCustomItemListByCustomProductId(long id);

}
