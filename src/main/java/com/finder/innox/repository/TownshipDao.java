package com.finder.innox.repository;

import java.util.List;

import com.finder.innox.core.domain.Township;

public interface TownshipDao extends GenericDao<Township, Long> {

	List<Township> searchTownshipList();

}
