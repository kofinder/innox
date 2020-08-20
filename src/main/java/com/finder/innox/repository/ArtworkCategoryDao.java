package com.finder.innox.repository;

import java.util.List;

import com.finder.innox.core.domain.ArtworkCategory;

public interface ArtworkCategoryDao extends GenericDao<ArtworkCategory, Long> {

	List<ArtworkCategory> getArtworkCategoryList();

}
