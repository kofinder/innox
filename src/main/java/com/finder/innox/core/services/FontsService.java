package com.finder.innox.core.services;

import java.util.List;

import com.finder.innox.core.dto.FontsDTO;

public interface FontsService {

	FontsDTO getFontDataById(long fontId);

	List<FontsDTO> getFontList(int status);

	FontsDTO fontManage(FontsDTO fontsDTO);

}
