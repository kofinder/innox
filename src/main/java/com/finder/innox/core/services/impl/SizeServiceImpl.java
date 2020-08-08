package com.finder.innox.core.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finder.innox.core.domain.Size;
import com.finder.innox.core.dto.SizeDTO;
import com.finder.innox.core.services.SizeService;
import com.finder.innox.repository.SizeDao;

@Service
@Transactional
public class SizeServiceImpl implements SizeService {
	
	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private SizeDao sizeDao;

	@Override
	public List<SizeDTO> getAllSize() {
		List<Size> entityList = sizeDao.getAllSize();
		if (entityList == null || entityList.isEmpty()) {
			return new ArrayList<SizeDTO>();
		}

		List<SizeDTO> dtoList = new ArrayList<>();
		entityList.forEach(size -> {
			SizeDTO sizeDto = new SizeDTO(size);
			dtoList.add(sizeDto);
		});
		logger.info("getAllSize() >> Sise List : " + dtoList.size());
		return dtoList;
	}

}
