package com.finder.innox.core.services;

import java.util.List;

import com.finder.innox.core.dto.ArtworkDTO;

public interface ArtworkService {

	ArtworkDTO uploadArtwork(ArtworkDTO artworkDTO);

	List<ArtworkDTO> getArtworkListByCategory(long artworkCategoryId);

	List<ArtworkDTO> getArtworkListByDesigner(long designerId);

	List<ArtworkDTO> searchArtworkList(String startDate, String endDate, Integer status, Long designerId,
			Integer pageNo);

	ArtworkDTO getArtworkDataById(long artworkId);

}
