package com.finder.innox.repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.finder.innox.core.domain.PaymentType;
import com.finder.innox.repository.PaymentTypeDao;

@SuppressWarnings({ "deprecation", "unchecked" })
@Repository
public class PaymentTypeDaoImpl extends GenericDaoImpl<PaymentType, Long> implements PaymentTypeDao {

	@Override
	public List<PaymentType> getAllPaymentTypeList(int status) {
		Criteria c = this.getCurrentSession().createCriteria(PaymentType.class);
		if (status > 0) {
			c.add(Restrictions.eq("status", status));
		}
		return c.list();
	}

	@Override
	public PaymentType getPaymentTypeByCode(String paymentTypeCode) {
		SQLQuery sqlQuery = getCurrentSession()
				.createSQLQuery("SELECT * FROM payment_type WHERE LOWER(TRIM(code)) = :code LIMIT 1");
		sqlQuery.setParameter("code", paymentTypeCode.trim().toLowerCase());
		sqlQuery.addEntity(PaymentType.class);
		return (PaymentType) sqlQuery.uniqueResult();
	}

}
