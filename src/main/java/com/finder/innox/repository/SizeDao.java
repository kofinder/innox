package com.finder.innox.repository;

import java.util.List;

import com.finder.innox.core.domain.Size;

public interface SizeDao extends GenericDao<Size, Long>{
	
	List<Size> getAllSize();

}
