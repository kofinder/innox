package com.finder.innox.core.services;

import java.util.List;

import com.finder.innox.core.dto.SizeDTO;

public interface SizeService {
	
	List<SizeDTO> getAllSize(int status);
	
	SizeDTO getSizeDataById(long sizeId);
	
	SizeDTO sizeManage(SizeDTO sizeDTO);

}
