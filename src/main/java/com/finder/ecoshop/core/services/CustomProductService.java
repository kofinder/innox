package com.finder.ecoshop.core.services;

import java.util.List;

import com.finder.ecoshop.core.dto.CustomProductDTO;

public interface CustomProductService {
	
	CustomProductDTO manageCustomProduct(CustomProductDTO customProductDTO, String loginUserName);
	
	CustomProductDTO getCustomProductById(long id);

	List<CustomProductDTO> searchCustomProduct(CustomProductDTO searchCusPrdDTO);

}
