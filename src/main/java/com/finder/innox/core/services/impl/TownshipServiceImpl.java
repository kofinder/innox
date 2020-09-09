package com.finder.innox.core.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finder.innox.core.domain.State;
import com.finder.innox.core.domain.Township;
import com.finder.innox.core.domain.User;
import com.finder.innox.core.domain.Zone;
import com.finder.innox.core.dto.TownshipDTO;
import com.finder.innox.core.services.TownshipService;
import com.finder.innox.repository.TownshipDao;
import com.finder.innox.repository.UserDao;
import com.finder.innox.utils.UserRoleEnum;

@Service
@Transactional
public class TownshipServiceImpl implements TownshipService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TownshipDao townshipDao;

	@Autowired
	private UserDao userDao;

	@Override
	public List<TownshipDTO> searchTownshipList() {
		List<Township> entityList = townshipDao.searchTownshipList();
		if (entityList == null || entityList.isEmpty()) {
			return new ArrayList<TownshipDTO>();
		}
		List<TownshipDTO> dtoList = new ArrayList<TownshipDTO>();
		entityList.forEach(township -> {
			dtoList.add(new TownshipDTO(township));
		});
		logger.info("searchTownshipList() >> Township List : " + dtoList.size());
		return dtoList;
	}

	@Override
	public TownshipDTO getTownshipDataById(long townshipId) {
		logger.info("getTownshipDataById() >> Township Id : " + townshipId);
		Township township = townshipDao.get(townshipId);
		if (township != null) {
			return new TownshipDTO(township);
		}
		return null;
	}

	@Override
	public TownshipDTO townshipManage(TownshipDTO townshipDTO, String authUserName) throws Exception {
		logger.info("townshipManage() >> Start");
		Township township = null;
		if (townshipDTO != null && townshipDTO.getSeq() > 0) {
			township = townshipDao.get(townshipDTO.getSeq());
			township.setUpdatedTime(new Date());
		} else {
			township = new Township();
			township.setCreatedTime(new Date());
		}

		User user = userDao.findByUserName(authUserName, UserRoleEnum.ROLE_ADMIN.getCode());
		if (user != null) {
			township.setUser(user);
		}

		State state = new State();
		state.setSeq(townshipDTO.getStateDTO().getSeq());
		township.setState(state);

		Zone zone = new Zone();
		zone.setSeq(townshipDTO.getZoneDTO().getSeq());
		township.setZone(zone);

		township.setTownshipName(townshipDTO.getTownshipName());
		township.setNrcName(townshipDTO.getNrcName());

		townshipDao.saveOrUpdate(township);
		logger.info("townshipManage() >> End");
		return new TownshipDTO(townshipDao.get(township.getSeq()));
	}

	@Override
	public List<TownshipDTO> getTownshipListByState(Long stateId) {
		logger.info("getTownshipListByState() >> State Id : " + stateId);
		List<Township> entityList = townshipDao.getTownshipListByState(stateId);
		if (entityList == null || entityList.isEmpty()) {
			return new ArrayList<TownshipDTO>();
		}

		List<TownshipDTO> dtoList = new ArrayList<TownshipDTO>();
		entityList.forEach(township -> {
			dtoList.add(new TownshipDTO(township));
		});
		logger.info("getTownshipListByState() >> State List : " + dtoList.size());
		return dtoList;
	}
}
