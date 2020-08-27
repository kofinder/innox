package com.finder.innox.core.services;

import java.util.List;

import com.finder.innox.core.dto.ZoneDTO;

public interface ZoneService {

	List<ZoneDTO> getAllZoneList();
	
	ZoneDTO getZoneDataById(long zoneId);
	
	ZoneDTO zoneManage(ZoneDTO zoneDTO) throws Exception;
}
