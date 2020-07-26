package com.finder.ecoshop.core.services;

import java.util.List;

import com.finder.ecoshop.core.dto.CustomItemDTO;

public interface CustomItemService {

	List<CustomItemDTO> getCustomItemListByCustomProductId(long id);

	CustomItemDTO customItemManage(CustomItemDTO customItemDTO) throws Exception;

	CustomItemDTO getCustomItemById(long customItemId);
}
