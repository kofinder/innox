package com.finder.innox.core.services;

import java.util.List;

import com.finder.innox.core.dto.ArtworkTagDTO;

public interface ArtworkTagService {

	List<ArtworkTagDTO> getArtworkTagByCategoryId(long categoryId);
}
