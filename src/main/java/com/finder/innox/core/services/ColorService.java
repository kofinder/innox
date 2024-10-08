package com.finder.innox.core.services;

import java.util.List;

import com.finder.innox.core.dto.ColorDTO;

public interface ColorService {

	List<ColorDTO> getAllColorList(int status);

	ColorDTO getColorDTO(Long colorId);

	ColorDTO colorManage(ColorDTO colorDTO);

}
