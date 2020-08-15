package com.finder.innox.core.services.impl;

import java.util.ArrayList;
import java.util.Date;
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
import com.finder.innox.utils.CommonStatus;

@Service
@Transactional
public class SizeServiceImpl implements SizeService {

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private SizeDao sizeDao;

	@Override
	public List<SizeDTO> getAllSize(int status) {
		List<Size> entityList = sizeDao.getAllSize(status);
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

	@Override
	public SizeDTO getSizeDataById(long sizeId) {
		logger.info("getSizeDataById() >> Sise Id : " + sizeId);
		Size size = sizeDao.get(sizeId);
		if (size != null) {
			SizeDTO sizeDTO = new SizeDTO(size);
			return sizeDTO;
		}
		return new SizeDTO();
	}

	@Override
	public SizeDTO sizeManage(SizeDTO sizeDTO) {
		logger.info("sizeManage() >> Start");
		Size size = null;
		if (sizeDTO.getSeq() > 0) {
			size = sizeDao.get(sizeDTO.getSeq());
			size.setUpdatedTime(new Date());
			size.setStatus(sizeDTO.getStatus());
		} else {
			size = new Size();
			size.setStatus(CommonStatus.ACTIVE.getCode());
			size.setCreatedTime(new Date());
		}

		size.setSizeCategory(sizeDTO.getSizeCategory());
		size.setSizeName(sizeDTO.getSizeName());
		size.setSizeCode(sizeDTO.getSizeCode());

		sizeDao.saveOrUpdate(size);
		logger.info("sizeManage() >> End >> Size Id : " + size.getSeq());
		return new SizeDTO(sizeDao.get(size.getSeq()));
	}

}
