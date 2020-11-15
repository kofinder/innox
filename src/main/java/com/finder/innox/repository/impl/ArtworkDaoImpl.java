package com.finder.innox.repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.finder.innox.core.domain.Artwork;
import com.finder.innox.core.dto.ArtworkDTO;
import com.finder.innox.repository.ArtworkDao;
import com.finder.innox.utils.CommonConstant;
import com.finder.innox.utils.CommonUtil;

@SuppressWarnings({ "unchecked", "deprecation" })
@Repository
public class ArtworkDaoImpl extends GenericDaoImpl<Artwork, Long> implements ArtworkDao {

	@Override
	public List<ArtworkDTO> getArtworkByCategroyId(long artworkCategoryId) {
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append(
				"SELECT a.id AS seq, a.artwork_name AS artworkName, a.sell_price AS sellPrice, a.artwork_image AS artworkImage");
		sqlBuilder.append(
				" FROM artwork AS a WHERE id IN (SELECT artwork_id FROM artwork_tags AS t WHERE artwork_category_id = "
						+ artworkCategoryId + ")");

		Query<ArtworkDTO> query = this.getCurrentSession().createSQLQuery(sqlBuilder.toString())

				.addScalar("seq", StandardBasicTypes.LONG).addScalar("artworkName", StandardBasicTypes.STRING)
				.addScalar("sellPrice", StandardBasicTypes.BIG_DECIMAL)
				.addScalar("artworkImage", StandardBasicTypes.STRING);

		query.setResultTransformer(Transformers.aliasToBean(ArtworkDTO.class));
		List<ArtworkDTO> result = query.list();
		return result;
	}

	@Override
	public List<Artwork> getArtworkListByDesigner(long designerId) {
		Criteria c = this.getCurrentSession().createCriteria(Artwork.class);
		c.add(Restrictions.eq("designer.id", designerId));
		c.addOrder(Order.asc("artworkName"));
		return c.list();
	}

	@Override
	public List<Artwork> searchArtworkList(String startDate, String endDate, Integer status, Long designerId,
			Integer pageNo) {

		Criteria c = this.getCurrentSession().createCriteria(Artwork.class);

		if (designerId != null && designerId.compareTo(0L) > 0) {
			c.add(Restrictions.eq("designer.userSeq", designerId));
		}

		if (status != null && status.compareTo(0) > 0) {
			c.add(Restrictions.eq("status", status));
		}

		if (!CommonUtil.isEmpty(startDate) || !CommonUtil.isEmpty(endDate)) {
			if (!CommonUtil.isEmpty(startDate) && !CommonUtil.isEmpty(endDate)) {
				c.add(Restrictions.ge("createdTime",
						CommonUtil.changeStringToDate(CommonConstant.STD_DATE_FORMAT_dd_MM_yyyy_hh_mm_ss_a,
								CommonUtil.changeFormatOfDateString(CommonConstant.STD_DATE_FORMAT,
										CommonConstant.STD_DATE_FORMAT_dd_MM_yyyy_hh_mm_ss_a, startDate))));
				c.add(Restrictions.lt("createdTime",
						CommonUtil.addDays(
								CommonUtil
										.changeStringToDate(CommonConstant.STD_DATE_FORMAT_dd_MM_yyyy_hh_mm_ss_a,
												CommonUtil.changeFormatOfDateString(CommonConstant.STD_DATE_FORMAT,
														CommonConstant.STD_DATE_FORMAT_dd_MM_yyyy_hh_mm_ss_a, endDate)),
								1)));

			} else if (!CommonUtil.isEmpty(startDate) && CommonUtil.isEmpty(endDate)) {
				c.add(Restrictions.ge("createdTime",
						CommonUtil.changeStringToDate(CommonConstant.STD_DATE_FORMAT_dd_MM_yyyy_hh_mm_ss_a,
								CommonUtil.changeFormatOfDateString(CommonConstant.STD_DATE_FORMAT,
										CommonConstant.STD_DATE_FORMAT_dd_MM_yyyy_hh_mm_ss_a, startDate))));
			} else {
				c.add(Restrictions.lt("createdTime",
						CommonUtil.addDays(
								CommonUtil
										.changeStringToDate(CommonConstant.STD_DATE_FORMAT_dd_MM_yyyy_hh_mm_ss_a,
												CommonUtil.changeFormatOfDateString(CommonConstant.STD_DATE_FORMAT,
														CommonConstant.STD_DATE_FORMAT_dd_MM_yyyy_hh_mm_ss_a, endDate)),
								1)));
			}
		}

		if (pageNo != null && pageNo.compareTo(0) > 0) {
			c.setFirstResult((pageNo - 1) * CommonConstant.ROW_PER_PAGE);
			c.setMaxResults(CommonConstant.ROW_PER_PAGE);
		}
		return c.list();
	}

}
