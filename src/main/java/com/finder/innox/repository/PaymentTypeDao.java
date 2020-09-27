package com.finder.innox.repository;

import java.util.List;

import com.finder.innox.core.domain.PaymentType;

public interface PaymentTypeDao extends GenericDao<PaymentType, Long>{
	
	List<PaymentType> getAllPaymentTypeList(int status);
	
	PaymentType getPaymentTypeByCode(String paymentTypeCode);

}
