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

}
