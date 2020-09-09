package com.finder.innox.core.services;

import java.util.List;

import com.finder.innox.core.dto.TownshipDTO;

public interface TownshipService {

	List<TownshipDTO> searchTownshipList();

	TownshipDTO getTownshipDataById(long townshipId);

	TownshipDTO townshipManage(TownshipDTO townshipDTO, String authUserName) throws Exception;

	List<TownshipDTO> getTownshipListByState(Long stateId);

}
