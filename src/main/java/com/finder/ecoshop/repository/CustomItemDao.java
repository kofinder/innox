package com.finder.ecoshop.repository;

import java.util.List;

import com.finder.ecoshop.core.domain.CustomItem;

public interface CustomItemDao extends GenericDao<CustomItem, Long> {

	List<CustomItem> getCustomItemListByCustomProductId(long id);

}
