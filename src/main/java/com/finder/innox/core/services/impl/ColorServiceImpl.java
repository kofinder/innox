package com.finder.innox.core.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finder.innox.core.domain.Color;
import com.finder.innox.core.dto.ColorDTO;
import com.finder.innox.core.services.ColorService;
import com.finder.innox.repository.ColorDao;
import com.finder.innox.utils.CommonStatus;

@Service
@Transactional
public class ColorServiceImpl implements ColorService {

	private Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private ColorDao colorDao;

	@Override
	public List<ColorDTO> getAllColorList(int status) {
		List<Color> entityList = colorDao.getAllColorList(status);
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

	@Override
	public ColorDTO getColorDTO(Long colorId) {
		logger.info("getColorDTO() >> Color Id : " + colorId);
		Color color = colorDao.get(colorId);
		if (color != null) {
			ColorDTO colorDTO = new ColorDTO(color);
			return colorDTO;
		}
		return null;
	}

	@Override
	public ColorDTO colorManage(ColorDTO colorDTO) {
		logger.info("colorManage() >> Start");
		Color color = null;
		if (colorDTO.getSeq() > 0) {
			color = colorDao.get(colorDTO.getSeq());
			color.setStatus(colorDTO.getStatus());
		} else {
			color = new Color();
			color.setCreatedTime(new Date());
			color.setStatus(CommonStatus.ACTIVE.getCode());
		}

		color.setColorName(colorDTO.getColorName());
		color.setColorCode(colorDTO.getColorCode());
		color.setUpdatedTime(new Date());

		colorDao.saveOrUpdate(color);

		logger.info("colorManage() >> End >> Color Id : " + color.getSeq());
		return new ColorDTO(colorDao.get(color.getSeq()));
	}

}
