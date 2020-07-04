package com.finder.ecoshop.repository;

import java.util.List;

import com.finder.ecoshop.core.domain.Size;

public interface SizeDao extends GenericDao<Size, Long>{
	
	List<Size> getAllSize();

}
