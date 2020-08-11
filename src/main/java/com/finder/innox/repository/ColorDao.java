package com.finder.innox.repository;

import java.util.List;

import com.finder.innox.core.domain.Color;

public interface ColorDao extends GenericDao<Color, Long>{

	List<Color> getAllColorList(int status);
}
