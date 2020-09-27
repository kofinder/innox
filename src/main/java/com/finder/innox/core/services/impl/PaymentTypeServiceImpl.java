package com.finder.innox.core.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finder.innox.core.domain.PaymentType;
import com.finder.innox.core.dto.PaymentTypeDTO;
import com.finder.innox.core.services.PaymentTypeService;
import com.finder.innox.repository.PaymentTypeDao;

@Service
@Transactional
public class PaymentTypeServiceImpl implements PaymentTypeService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PaymentTypeDao paymentTypeDao;

	@Override
	public List<PaymentTypeDTO> getAllPaymentTypeList(int status) {
		logger.info("getAllPaymentTypeList() >> Status : " + status);
		List<PaymentType> entityList = paymentTypeDao.getAllPaymentTypeList(status);
		if (entityList == null || entityList.isEmpty()) {
			return new ArrayList<PaymentTypeDTO>();
		}

		List<PaymentTypeDTO> dtoList = new ArrayList<PaymentTypeDTO>();
		entityList.forEach(payment -> {
			dtoList.add(new PaymentTypeDTO(payment));
		});
		return dtoList;
	}

}
