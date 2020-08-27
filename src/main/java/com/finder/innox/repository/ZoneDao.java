package com.finder.innox.repository;

import java.util.List;

import com.finder.innox.core.domain.Zone;

public interface ZoneDao extends GenericDao<Zone, Long>{
	
	List<Zone> getAllZoneList();
}
