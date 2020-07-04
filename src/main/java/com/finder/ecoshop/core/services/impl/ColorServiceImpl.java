package com.finder.ecoshop.core.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finder.ecoshop.core.domain.Color;
import com.finder.ecoshop.core.dto.ColorDTO;
import com.finder.ecoshop.core.services.ColorService;
import com.finder.ecoshop.repository.ColorDao;

@Service
@Transactional
public class ColorServiceImpl implements ColorService {

	private Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private ColorDao colorDao;

	@Override
	public List<ColorDTO> getAllColorList() {
		List<Color> entityList = colorDao.getAll();
		if (entityList == null || entityList.isEmpty()) {
			return new ArrayList<ColorDTO>();
		}

		List<ColorDTO> colorDtoList = new ArrayList<ColorDTO>();
		entityList.forEach(color -> {
			ColorDTO dto = new ColorDTO(color);
			colorDtoList.add(dto);
		});
		logger.info("getAllColorList() >> Color list : " + colorDtoList.size());
		return colorDtoList;
	}

}
