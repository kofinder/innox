package com.finder.innox.repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.finder.innox.core.domain.Order;
import com.finder.innox.core.dto.OrderDTO;
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

	@Override
	public List<Order> searchOrderHistoryList(OrderDTO orderDTO) {
		Criteria c = this.getCurrentSession().createCriteria(Order.class);
		c.createAlias("customer", "customer");

		if (!CommonUtil.isEmpty(orderDTO.getCustomerName())) {
			c.add(Restrictions.ilike("customer.userName", orderDTO.getCustomerName(), MatchMode.ANYWHERE));
		}

		if (!CommonUtil.isEmpty(orderDTO.getInvoiceNumber())) {
			c.add(Restrictions.ilike("invoiceNumber", orderDTO.getInvoiceNumber(), MatchMode.ANYWHERE));
		}

		if (orderDTO.getOrderStatus() > 0) {
			c.add(Restrictions.eq("orderStatus", orderDTO.getOrderStatus()));
		}

		if (orderDTO.getPaymentStatus() > 0) {
			c.add(Restrictions.eq("paymentStatus", orderDTO.getPaymentStatus()));
		}

		if (!CommonUtil.isEmpty(orderDTO.getFromDate()) || !CommonUtil.isEmpty(orderDTO.getToDate())) {
			if (!CommonUtil.isEmpty(orderDTO.getFromDate()) && !CommonUtil.isEmpty(orderDTO.getToDate())) {
				c.add(Restrictions.ge("orderDate",
						CommonUtil.changeStringToDate(CommonConstant.STD_DATE_FORMAT_dd_MM_yyyy_hh_mm_ss_a,
								CommonUtil.changeFormatOfDateString(CommonConstant.STD_DATE_FORMAT_MM_dd_yyyy,
										CommonConstant.STD_DATE_FORMAT_dd_MM_yyyy_hh_mm_ss_a,
										orderDTO.getFromDate()))));
				c.add(Restrictions.lt("orderDate", CommonUtil.addDays(
						CommonUtil.changeStringToDate(CommonConstant.STD_DATE_FORMAT_dd_MM_yyyy_hh_mm_ss_a,
								CommonUtil.changeFormatOfDateString(CommonConstant.STD_DATE_FORMAT_MM_dd_yyyy,
										CommonConstant.STD_DATE_FORMAT_dd_MM_yyyy_hh_mm_ss_a, orderDTO.getToDate())),
						1)));

			} else if (!CommonUtil.isEmpty(orderDTO.getFromDate()) && CommonUtil.isEmpty(orderDTO.getToDate())) {
				c.add(Restrictions.ge("orderDate",
						CommonUtil.changeStringToDate(CommonConstant.STD_DATE_FORMAT_dd_MM_yyyy_hh_mm_ss_a,
								CommonUtil.changeFormatOfDateString(CommonConstant.STD_DATE_FORMAT_MM_dd_yyyy,
										CommonConstant.STD_DATE_FORMAT_dd_MM_yyyy_hh_mm_ss_a,
										orderDTO.getFromDate()))));
			} else {
				c.add(Restrictions.lt("orderDate", CommonUtil.addDays(
						CommonUtil.changeStringToDate(CommonConstant.STD_DATE_FORMAT_dd_MM_yyyy_hh_mm_ss_a,
								CommonUtil.changeFormatOfDateString(CommonConstant.STD_DATE_FORMAT_MM_dd_yyyy,
										CommonConstant.STD_DATE_FORMAT_dd_MM_yyyy_hh_mm_ss_a, orderDTO.getToDate())),
						1)));
			}
		}

		c.addOrder(org.hibernate.criterion.Order.desc("orderDate"));
		return c.list();
	}

	@Override
	public void updateOrderStatus(long orderId, int orderStatus, int paymentStatus) {
		String sql = "UPDATE `orders` SET order_status=:orderStatus, payment_status=:paymentStatus WHERE id=:id";

		Query<Order> query = this.getCurrentSession().createSQLQuery(sql);
		query.setParameter("id", orderId);
		query.setParameter("orderStatus", orderStatus);
		query.setParameter("paymentStatus", paymentStatus);

		query.executeUpdate();
	}

}
