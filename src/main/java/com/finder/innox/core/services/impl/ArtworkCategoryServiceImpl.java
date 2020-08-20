package com.finder.innox.core.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finder.innox.core.domain.ArtworkCategory;
import com.finder.innox.core.dto.ArtworkCategoryDTO;
import com.finder.innox.core.services.ArtworkCategoryService;
import com.finder.innox.repository.ArtworkCategoryDao;

@Service
@Transactional
public class ArtworkCategoryServiceImpl implements ArtworkCategoryService {

	@Autowired
	private ArtworkCategoryDao artworkCategoryDao;

	@Override
	public List<ArtworkCategoryDTO> getArtworkCategoryList() {
		List<ArtworkCategory> entityList = artworkCategoryDao.getArtworkCategoryList();
		if (entityList == null || entityList.isEmpty()) {
			return new ArrayList<ArtworkCategoryDTO>();
		}

		List<ArtworkCategoryDTO> dtoList = new ArrayList<ArtworkCategoryDTO>();
		entityList.forEach(entity -> {
			ArtworkCategoryDTO dto = new ArtworkCategoryDTO(entity);
			dtoList.add(dto);
		});
		return dtoList;
	}

}
