package com.finder.innox.core.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finder.innox.core.domain.Fonts;
import com.finder.innox.core.dto.FontsDTO;
import com.finder.innox.core.services.FontsService;
import com.finder.innox.repository.FontsDao;
import com.finder.innox.utils.CommonConstant;
import com.finder.innox.utils.CommonStatus;
import com.finder.innox.utils.CommonUtil;
import com.finder.innox.utils.ImagesUtil;

@Service
@Transactional
public class FontsServiceImpl implements FontsService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private FontsDao fontsDao;

	@Override
	public FontsDTO getFontDataById(long fontId) {
		logger.info("getFontDataById() >> Font Id : " + fontId);
		Fonts font = fontsDao.get(fontId);
		if (font != null) {
			return new FontsDTO(font);
		}
		return new FontsDTO();
	}

	@Override
	public List<FontsDTO> getFontList(int status) {
		List<Fonts> fonts = fontsDao.getFontList(status);
		if (fonts == null || fonts.isEmpty()) {
			return new ArrayList<FontsDTO>();
		}
		List<FontsDTO> fontsDtoList = new ArrayList<FontsDTO>();
		fonts.forEach(font -> {
			fontsDtoList.add(new FontsDTO(font));
		});
		logger.info("getFontList() >> Font List : " + fontsDtoList.size());
		return fontsDtoList;
	}

	@Override
	public FontsDTO fontManage(FontsDTO fontsDTO) {
		Fonts fonts = null;
		if (fontsDTO.getSeq() > 0) {
			fonts = fontsDao.get(fontsDTO.getSeq());
			fonts.setStatus(fontsDTO.getStatus());
			fonts.setUpdatedTime(new Date());
		} else {
			fonts = new Fonts();
			fonts.setStatus(CommonStatus.ACTIVE.getCode());
			fonts.setCreatedTime(new Date());
		}

		fonts.setName(fontsDTO.getName());
		fonts.setFontURL(fontsDTO.getFontURL());
		fonts.setFontSample(fontsDTO.getFontSample());
		fonts.setFontDescription(fontsDTO.getFontDescription());

		fontsDao.saveOrUpdate(fonts);

		if (!CommonUtil.isEmpty(fontsDTO.getFontImageFile().getOriginalFilename())) {
			try {
				String imageName = ImagesUtil.uploadMultipartFile(fontsDTO.getFontImageFile(),
						CommonConstant.FONTS_DIRECTORY + CommonConstant.FONTS_IMAGE_DIRECTORY + fonts.getSeq() + "/",
						CommonConstant.FONTS_IMAGE_PERFIX, fonts.getSeq());
				fonts.setFontImage(imageName);
			} catch (Exception e) {
				e.printStackTrace();
			}
			fontsDao.merge(fonts);
		}

		logger.info("fontManage() >> Fonts Id : " + fonts.getSeq());
		return new FontsDTO(fontsDao.get(fonts.getSeq()));
	}
}
