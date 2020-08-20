package com.finder.innox.core.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finder.innox.core.domain.ArtworkTag;
import com.finder.innox.core.dto.ArtworkTagDTO;
import com.finder.innox.core.services.ArtworkTagService;
import com.finder.innox.repository.ArtworkTagDao;

@Service
@Transactional
public class ArtworkTagServiceImpl implements ArtworkTagService {

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private ArtworkTagDao artworkTagDao;

	@Override
	public List<ArtworkTagDTO> getArtworkTagByCategoryId(long categoryId) {
		logger.info("getArtworkTagByCategoryId() >> Start >> Artwork Category Id : " + categoryId);

		List<ArtworkTag> entityList = artworkTagDao.getArtworkTagByCategoryId(categoryId);
		if (entityList == null || entityList.isEmpty()) {
			return new ArrayList<ArtworkTagDTO>();
		}

		List<ArtworkTagDTO> dtoList = new ArrayList<ArtworkTagDTO>();
		entityList.forEach(entity -> {
			ArtworkTagDTO dto = new ArtworkTagDTO(entity);
			dtoList.add(dto);
		});
		return dtoList;
	}

}
