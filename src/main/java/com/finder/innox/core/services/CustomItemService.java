package com.finder.innox.core.services;

import java.util.List;

import com.finder.innox.core.dto.CustomItemDTO;

public interface CustomItemService {

	List<CustomItemDTO> getCustomItemListByCustomProductId(long id, int status);

	CustomItemDTO customItemManage(CustomItemDTO customItemDTO) throws Exception;

	CustomItemDTO getCustomItemById(long customItemId);
}
