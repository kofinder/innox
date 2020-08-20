package com.finder.innox.repository;

import java.util.List;

import com.finder.innox.core.domain.ArtworkTag;

public interface ArtworkTagDao extends GenericDao<ArtworkTag, Long> {

	List<ArtworkTag> getArtworkTagByCategoryId(long categoryId);

}
