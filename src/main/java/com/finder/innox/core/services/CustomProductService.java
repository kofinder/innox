package com.finder.innox.core.services;

import java.util.List;

import com.finder.innox.core.dto.CustomProductDTO;

public interface CustomProductService {

	CustomProductDTO manageCustomProduct(CustomProductDTO customProductDTO, String loginUserName);

	CustomProductDTO getCustomProductById(long id);

	List<CustomProductDTO> searchCustomProduct(CustomProductDTO searchCusPrdDTO);

	List<CustomProductDTO> getCustomProductListBySubCat(long subCategoryId);

}
