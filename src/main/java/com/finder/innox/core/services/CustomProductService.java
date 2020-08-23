package com.finder.innox.core.services;

import java.util.List;

import com.finder.innox.core.dto.CustomProductDTO;

public interface CustomProductService {

	CustomProductDTO manageCustomProduct(CustomProductDTO customProductDTO, String loginUserName) throws Exception;

	CustomProductDTO getCustomProductById(long id, int status);

	List<CustomProductDTO> searchCustomProduct(CustomProductDTO searchCusPrdDTO);

	List<CustomProductDTO> getCustomProductListBySubCat(long subCategoryId);

}
