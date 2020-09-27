package com.finder.innox.core.services;

import java.util.List;

import com.finder.innox.core.dto.PaymentTypeDTO;

public interface PaymentTypeService {
	
	List<PaymentTypeDTO> getAllPaymentTypeList(int status);

}
