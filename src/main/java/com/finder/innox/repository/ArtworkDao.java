package com.finder.innox.repository;

import java.util.List;

import com.finder.innox.core.domain.Artwork;
import com.finder.innox.core.dto.ArtworkDTO;

public interface ArtworkDao extends GenericDao<Artwork, Long> {

	List<ArtworkDTO> getArtworkByCategroyId(long artworkCategoryId);

	List<Artwork> getArtworkListByDesigner(long designerId);

}
