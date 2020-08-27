package com.finder.innox.core.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finder.innox.core.domain.State;
import com.finder.innox.core.domain.User;
import com.finder.innox.core.dto.StateDTO;
import com.finder.innox.core.services.StateService;
import com.finder.innox.core.services.UserService;
import com.finder.innox.repository.StateDao;
import com.finder.innox.repository.UserDao;
import com.finder.innox.utils.CommonUtil;

@Service
@Transactional
public class StateServiceImpl implements StateService {

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private StateDao stateDao;

	@Autowired
	private UserDao userDao;

	@Override
	public StateDTO getStateDataById(long stateId) {
		logger.info("getStateDataById() >> Start >> State Id : " + stateId);
		State entity = stateDao.getStateDataById(stateId);
		if (entity != null) {
			return new StateDTO(entity);
		}
		return null;
	}

	@Override
	public List<StateDTO> getStateDataList() {
		logger.debug("getStateDataList() >> Start");
		List<State> entityList = stateDao.getStateDataList();
		if (entityList == null && entityList.isEmpty()) {
			return new ArrayList<StateDTO>();
		}

		List<StateDTO> dtoList = new ArrayList<StateDTO>();
		entityList.forEach(entity -> {
			StateDTO stateDto = new StateDTO(entity);
			dtoList.add(stateDto);
		});
		logger.debug("getStateDataList() >> End >> State List : " + dtoList.size());
		return dtoList;
	}

	@Override
	public StateDTO stateManage(StateDTO stateDTO, String authUserName) throws Exception {
		logger.info("stateManage() >> Start");
		State state = null;
		if (stateDTO.getSeq() > 0) {
			state = stateDao.get(stateDTO.getSeq());
			state.setUpdatedTime(new Date());
		} else {
			state = new State();
			state.setCreatedTime(new Date());
		}

		state.setName(stateDTO.getName());
		state.setStateNo(stateDTO.getStateNo());

		if (!CommonUtil.isEmpty(authUserName)) {
			state.setUser(userDao.findByUserName(authUserName));
		}

		stateDao.saveOrUpdate(state);

		logger.info("stateManage() >> End >> Satate Id : " + state.getSeq());
		return new StateDTO(stateDao.get(state.getSeq()));
	}

}
