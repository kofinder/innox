package com.finder.innox.repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.finder.innox.core.domain.Order;
import com.finder.innox.repository.OrderDao;
import com.finder.innox.utils.CommonConstant;
import com.finder.innox.utils.CommonUtil;

@SuppressWarnings({ "deprecation", "unchecked" })
@Repository
public class OrderDaoImpl extends GenericDaoImpl<Order, Long> implements OrderDao {

	@Override
	public List<Order> getOrderHistory(List<Integer> orderStatusList, String startDate, String endDate) {
		Criteria c = this.getCurrentSession().createCriteria(Order.class);

		c.add(Restrictions.in("orderStatus", orderStatusList));

		if (!CommonUtil.isEmpty(startDate) || !CommonUtil.isEmpty(endDate)) {
			if (!CommonUtil.isEmpty(startDate) && !CommonUtil.isEmpty(endDate)) {
				c.add(Restrictions.ge("orderDate",
						CommonUtil.changeStringToDate(CommonConstant.STD_DATE_FORMAT_dd_MM_yyyy_hh_mm_ss_a,
								CommonUtil.changeFormatOfDateString(CommonConstant.STD_DATE_FORMAT,
										CommonConstant.STD_DATE_FORMAT_dd_MM_yyyy_hh_mm_ss_a, startDate))));
				c.add(Restrictions.lt("orderDate",
						CommonUtil.addDays(
								CommonUtil
										.changeStringToDate(CommonConstant.STD_DATE_FORMAT_dd_MM_yyyy_hh_mm_ss_a,
												CommonUtil.changeFormatOfDateString(CommonConstant.STD_DATE_FORMAT,
														CommonConstant.STD_DATE_FORMAT_dd_MM_yyyy_hh_mm_ss_a, endDate)),
								1)));

			} else if (!CommonUtil.isEmpty(startDate) && CommonUtil.isEmpty(endDate)) {
				c.add(Restrictions.ge("orderDate",
						CommonUtil.changeStringToDate(CommonConstant.STD_DATE_FORMAT_dd_MM_yyyy_hh_mm_ss_a,
								CommonUtil.changeFormatOfDateString(CommonConstant.STD_DATE_FORMAT,
										CommonConstant.STD_DATE_FORMAT_dd_MM_yyyy_hh_mm_ss_a, startDate))));
			} else {
				c.add(Restrictions.lt("orderDate",
						CommonUtil.addDays(
								CommonUtil
										.changeStringToDate(CommonConstant.STD_DATE_FORMAT_dd_MM_yyyy_hh_mm_ss_a,
												CommonUtil.changeFormatOfDateString(CommonConstant.STD_DATE_FORMAT,
														CommonConstant.STD_DATE_FORMAT_dd_MM_yyyy_hh_mm_ss_a, endDate)),
								1)));
			}
		}

		c.addOrder(org.hibernate.criterion.Order.desc("orderDate"));
		return c.list();
	}

}
