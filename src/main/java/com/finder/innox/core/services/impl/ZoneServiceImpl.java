package com.finder.innox.core.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finder.innox.core.domain.Zone;
import com.finder.innox.core.dto.ZoneDTO;
import com.finder.innox.core.services.ZoneService;
import com.finder.innox.repository.ZoneDao;

@Service
@Transactional
public class ZoneServiceImpl implements ZoneService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ZoneDao zoneDao;

	@Override
	public List<ZoneDTO> getAllZoneList() {
		List<Zone> zoneList = zoneDao.getAllZoneList();
		if (zoneList == null || zoneList.isEmpty()) {
			return new ArrayList<ZoneDTO>();
		}

		List<ZoneDTO> zoneDtoList = new ArrayList<ZoneDTO>();
		zoneList.forEach(zone -> {
			ZoneDTO zoneDTO = new ZoneDTO(zone);
			zoneDtoList.add(zoneDTO);
		});
		logger.info("getAllZoneList() >> Zone List : " + zoneDtoList.size());
		return zoneDtoList;
	}

	@Override
	public ZoneDTO getZoneDataById(long zoneId) {
		logger.info("getZoneDataById() >> Zone Id : " + zoneId);
		Zone zone = zoneDao.get(zoneId);
		if (zone == null) {
			return null;
		}
		return new ZoneDTO(zone);
	}

	@Override
	public ZoneDTO zoneManage(ZoneDTO zoneDTO) throws Exception {
		logger.info("zoneManage() >> Start");
		Zone zone = null;
		if (zoneDTO != null && zoneDTO.getSeq() > 0) {
			zone = zoneDao.get(zoneDTO.getSeq());
			zone.setUpdatedTime(new Date());
		} else {
			zone = new Zone();
			zone.setCreatedTime(new Date());
		}

		zone.setZoneName(zoneDTO.getZoneName());
		zone.setZoneCode(zoneDTO.getZoneCode());
		zone.setDeliveryFee(zoneDTO.getDeliveryFee());
		zone.setDescription(zoneDTO.getDescription());
		zone.setIsRequiredAddress(false);

		zoneDao.saveOrUpdate(zone);

		return new ZoneDTO(zone);
	}
}
