package com.finder.ecoshop.repository;

import java.util.List;

import com.finder.ecoshop.core.domain.CustomProduct;
import com.finder.ecoshop.core.dto.CustomProductDTO;

public interface CustomProductDao extends GenericDao<CustomProduct, Long> {

	List<CustomProduct> searchCustomProduct(CustomProductDTO searchCusPrdDTO);

}
