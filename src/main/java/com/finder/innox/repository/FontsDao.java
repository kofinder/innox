package com.finder.innox.repository;

import java.util.List;

import com.finder.innox.core.domain.Fonts;

public interface FontsDao extends GenericDao<Fonts, Long>{
	
	List<Fonts> getFontList(int status);

}
